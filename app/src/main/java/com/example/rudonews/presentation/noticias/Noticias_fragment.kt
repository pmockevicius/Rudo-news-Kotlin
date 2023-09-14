package com.example.rudonews.presentation.noticias

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.LoggedInActivity
import com.example.rudonews.MainActivity
import com.example.rudonews.R
import com.example.rudonews.databinding.FragmentNoticiasBinding
import com.example.rudonews.databinding.RegisterFragmentBinding


class Noticias_fragment : Fragment() {

lateinit var binding: FragmentNoticiasBinding
lateinit var noticiasSearchView : android.widget.SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoticiasBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onView()
    }

    private fun onView(){
        setNavbarTitle()
        setSearchViewListener()
        initNoticiasRecyclerView()
        initTagsRecyclerView()
    }

    private fun initNoticiasRecyclerView(){
        val recyclerView: RecyclerView = binding.NoticiasRecyclerView
        val dataList = listOf("Item 1", "Item 2", "Item 3", "Item 3", "Item 3", "Item 3") // Your data source
        val adapter = NoticiasAdapter(dataList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

//        recyclerView.layoutManager = LinearLayoutManager(requireContext(),  LinearLayoutManager.HORIZONTAL, false)


    }

    fun initTagsRecyclerView(){
        val recyclerView: RecyclerView = binding.TagsRecyclerView
        val dataList = listOf("Item 1", "Item 2", "Item 3", "Item 3", "Item 3", "Item 3") // Your data source
        val adapter = TagsAdapter(dataList)

        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.layoutManager = LinearLayoutManager(requireContext(),  LinearLayoutManager.HORIZONTAL, false)

    }

    private fun setNavbarTitle(){
        val activity = activity as? LoggedInActivity
        activity?.setNavBarText("Noticias")
    }

    private fun setSearchViewListener() {
        noticiasSearchView = binding.noticiasSearchView

        noticiasSearchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                noticiasSearchView.clearFocus()
                hideKeyboard()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                println("typing $newText")
                return true
            }
        })
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(noticiasSearchView.windowToken, 0)
    }



}