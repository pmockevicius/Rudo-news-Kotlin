package com.example.rudonews.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.rudonews.R
import com.example.rudonews.activities.DetailsActivity.DetailsActivity
import com.example.rudonews.databinding.ActivityMainBinding
import com.example.rudonews.domain.entity.Noticia
import com.example.rudonews.presentation.contrasena_olvidad.Contrasena_Olvidad_Fragment
import com.example.rudonews.presentation.departamentos.Departamentos_Fragment
import com.example.rudonews.presentation.login.Login_fragment
import com.example.rudonews.presentation.register.Register_fragment

class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    private lateinit var navBarTextView: TextView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.addOnBackStackChangedListener(this)

        navBarTextView = binding.navBarTextView


        val needsLogin = true
        if (needsLogin) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Login_fragment())
                .addToBackStack(null)
                .commit()
        } else {
        }

        setUpBackNavigationListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.removeOnBackStackChangedListener(this)
    }


    fun setUpBackNavigationListener(){
        var navBarBackArrow = findViewById<ImageView>(R.id.NavBarBackArrow)
        navBarBackArrow.setOnClickListener {
            println("clicked back")
            if (supportFragmentManager.backStackEntryCount > 1){
                supportFragmentManager.popBackStack()
            }

        }
    }


    fun setNavBarText(text: String){
        navBarTextView.text = text
    }

    fun navigateToRegisterPage(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, Register_fragment())
            .addToBackStack(null)
            .commit()
    }

    fun navigateToDepartamentosFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, Departamentos_Fragment())
            .addToBackStack(null)
            .commit()
    }

    fun navigateToOlvidadFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, Contrasena_Olvidad_Fragment() )
            .addToBackStack(null)
            .commit()
    }

    fun navigateToNoticiasActivity(){
        val intent = Intent(this, LoggedInActivity::class.java)
        startActivity(intent)
    }

    fun navigateToDetailsActivity(noticia: Noticia, context: Context){
        println("Opening ${noticia.title}")

        val intent = Intent(context, DetailsActivity::class.java)
        startActivity(intent)
    }

    override fun onBackStackChanged() {
        var navBarBackArrow = findViewById<ImageView>(R.id.NavBarBackArrow)
        if (supportFragmentManager.backStackEntryCount == 1) {
            navBarBackArrow.visibility = View.GONE
        } else {
            navBarBackArrow.visibility = View.VISIBLE
        }
    }

}
