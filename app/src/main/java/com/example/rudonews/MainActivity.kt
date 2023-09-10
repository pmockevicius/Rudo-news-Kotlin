package com.example.rudonews

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.rudonews.presentation.login.Login_fragment
import com.example.rudonews.presentation.register.Register_fragment

class MainActivity : AppCompatActivity() {

    private lateinit var navBarTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navBarTextView = findViewById(R.id.navBarTextView)

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


    fun setUpBackNavigationListener(){
        var navBarBackArrow = findViewById<ImageView>(R.id.NavBarBackArrow)
        navBarBackArrow.setOnClickListener {
            supportFragmentManager.popBackStack()
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

}
