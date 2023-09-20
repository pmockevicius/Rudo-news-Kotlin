package com.example.rudonews.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rudonews.activities.LoggedInActivity
import com.example.rudonews.R
import com.example.rudonews.databinding.FragmentFavoritesBinding
import com.example.rudonews.databinding.FragmentNoticiasBinding


class Favorites_fragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onView()


    }
    private fun onView() {
        setNavbarTitle()
    }

    private fun setNavbarTitle(){
        val activity = activity as? LoggedInActivity
        activity?.setNavBarText("Favoritos")
    }
}