package com.example.rudonews.presentation.noticias

import NoticiasAdapter
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.distinctUntilChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.LoggedInActivity
import com.example.rudonews.MainActivity
import com.example.rudonews.R
import com.example.rudonews.data.dataSource.auth.MockAuthDatasource
import com.example.rudonews.data.dataSource.auth.MockDataSource
import com.example.rudonews.data.repository.AuthRepository
import com.example.rudonews.data.repository.DataRepository
import com.example.rudonews.databinding.FragmentNoticiasBinding
import com.example.rudonews.databinding.RegisterFragmentBinding
import com.example.rudonews.domain.entity.Noticia
import com.example.rudonews.domain.usecase.AuthUsecase
import com.example.rudonews.domain.usecase.DataUsecase
import com.example.rudonews.presentation.login.LoginViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class Noticias_fragment : Fragment() {

    private lateinit var viewModel: NoticiasViewModel
    private lateinit var dataUsecase: DataUsecase
    private lateinit var dataRepository: DataRepository
    private lateinit var mockDatasource: MockDataSource

lateinit var binding: FragmentNoticiasBinding
lateinit var noticiasSearchView : android.widget.SearchView
    lateinit var noticiasRecyclerView: RecyclerView
    lateinit var noticiasAdapter: NoticiasAdapter
    lateinit var dataList: List<String>

    val scope = MainScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mockDatasource = MockDataSource()
        dataRepository = DataRepository(mockDatasource)
        dataUsecase = DataUsecase(dataRepository)
        viewModel = NoticiasViewModel(dataUsecase)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoticiasBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onView()
    }

    private fun onView(){
        initObservers()
        setNavbarTitle()
        setSearchViewListener()
        initNoticiasRecyclerView()
        initTagsRecyclerView()
        getNoticias()
    }

    private fun initObservers() {
        val observer = Observer<List<Noticia>> {
            println("response in fragment ${it.size} blablabla")
        }
        with(viewModel) {
            news.distinctUntilChanged()
                .observe(viewLifecycleOwner, observer)

        }
    }

    private fun initNoticiasRecyclerView(){
        val recyclerView: RecyclerView = binding.NoticiasRecyclerView
         dataList = listOf("Item 1", "Item 2", "Item 3", "Item 3", "Item 3", "Item 3") // Your data source
        noticiasAdapter = NoticiasAdapter(dataList)

        recyclerView.adapter = noticiasAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    fun initTagsRecyclerView(){
        val recyclerView: RecyclerView = binding.TagsRecyclerView
        val dataList = listOf("Item 1", "Item 2", "Item 3", "Item 3", "Item 3", "Item 3") // Your data source
        val adapter = TagsAdapter(dataList)

        recyclerView.adapter = adapter

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
               noticiasSearchView.clearFocus()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterData(newText)
                return true
            }
        })
    }

    private fun filterData(query: String?) {
        val filteredData = dataList.filter {
            it.contains(query.orEmpty(), ignoreCase = true)
        }
       noticiasAdapter.submitFilteredData(filteredData)
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(noticiasSearchView.windowToken, 0)
    }

    fun getNoticias(){
        scope.launch {
            val response = viewModel.getNoticias()
            println("response in fragment ${response.size}")
        }
    }
}