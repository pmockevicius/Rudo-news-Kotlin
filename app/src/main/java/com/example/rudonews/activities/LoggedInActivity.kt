package com.example.rudonews.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rudonews.R
import com.example.rudonews.databinding.ActivityLoggedinBinding
import com.example.rudonews.presentation.Favorites_fragment
import com.example.rudonews.presentation.departamentos.Departamentos_Fragment
import com.example.rudonews.presentation.editPerfil.Edit_Perfil_fragment
import com.example.rudonews.presentation.noticias.Noticias_fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoggedInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoggedinBinding
    private lateinit var navBarTextView: TextView
    private lateinit var bottomNavigation: BottomNavigationView

    private val noticiasFragment = Noticias_fragment()
    private val editPerfilFragment = Edit_Perfil_fragment()
    private val favoritesFragment = Favorites_fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoggedinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navBarTextView = binding.loggedInNavBarTextView
        bottomNavigation = binding.bottomNavBar

        initializeFragments()
        setupBottomNavigationBar()
    }

    private fun initializeFragments() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_loggedIn, noticiasFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupBottomNavigationBar() {


        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_noticias -> makeCurrentFragment(noticiasFragment)
                R.id.ic_perfil -> makeCurrentFragment(editPerfilFragment)
                R.id.ic_favorites -> makeCurrentFragment(favoritesFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_loggedIn, fragment)
            commit()
        }
    }

    fun setNavBarText(text: String) {
        navBarTextView.text = text
    }

    fun navigateToNoticiasFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_loggedIn, Noticias_fragment())
            .addToBackStack(null)
            .commit()

        bottomNavigation.selectedItemId = R.id.ic_noticias
    }
}
