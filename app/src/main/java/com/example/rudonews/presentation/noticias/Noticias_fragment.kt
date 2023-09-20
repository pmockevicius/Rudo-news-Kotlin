package com.example.rudonews.presentation.noticias

import NoticiasAdapter
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.activities.LoggedInActivity
import com.example.rudonews.data.dataSource.auth.MockDataSource
import com.example.rudonews.data.repository.DataRepository
import com.example.rudonews.databinding.FragmentNoticiasBinding
import com.example.rudonews.domain.entity.News
import com.example.rudonews.domain.entity.Tag
import com.example.rudonews.domain.usecase.DataUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class Noticias_fragment : Fragment() {

    private lateinit var viewModel: NoticiasViewModel
    private lateinit var dataUsecase: DataUsecase
    private lateinit var dataRepository: DataRepository
    private lateinit var mockDatasource: MockDataSource

    private lateinit var binding: FragmentNoticiasBinding
    private lateinit var noticiasSearchView: android.widget.SearchView
    private lateinit var noticiasAdapter: NoticiasAdapter
    private lateinit var dataList: List<News>
    private lateinit var tagsMessage: TextView
//    private lateinit var filteredDataList: List<Noticia>

    val scope = MainScope()
    var selectedTags: MutableList<String> = mutableListOf()
    var filteredDataList: List<News> = listOf()
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

        tagsMessage = binding.filtrosTextView

        scope.launch(Dispatchers.IO) {
            onView()
        }


    }

    private suspend fun onView() {
//        initObservers()
        setNavbarTitle()
        setSearchViewListener()
        getNoticias()
        initNoticiasRecyclerView()
        initTagsRecyclerView()
    }

//    private fun initObservers() {
//        val observer = Observer<List<Noticia>> {
//            println("response in fragment ${it.size} blablabla")
//        }
//        with(viewModel) {
//            news.distinctUntilChanged()
//                .observe(viewLifecycleOwner, observer)
//
//        }
//    }

    private suspend fun initNoticiasRecyclerView() {
        val recyclerView: RecyclerView = binding.NoticiasRecyclerView
        dataList = viewModel.getNoticias()
        noticiasAdapter = NoticiasAdapter(dataList)

        recyclerView.adapter = noticiasAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    suspend fun initTagsRecyclerView() {
        val recyclerView: RecyclerView = binding.TagsRecyclerView
        val tagsList = viewModel.getTags()
        val tagPressed: ((tag: String) -> Unit) = { tag ->
            saveTag(tag)
            filterWithChips(selectedTags)
        }
        val adapter = TagsAdapter(tagsList, tagPressed)

        recyclerView.adapter = adapter

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

    }

    private fun saveTag(tag: String) {
        selectedTags.run {
            if (contains(tag)) remove(tag) else add(tag)
        }
        displayFiltersMessage()
    }

    private fun displayFiltersMessage() {
        if (selectedTags.isNotEmpty()) {
            tagsMessage.visibility = View.VISIBLE
        } else {
            tagsMessage.visibility = View.GONE
        }
    }


    private fun setNavbarTitle() {
        val activity = activity as? LoggedInActivity
        activity?.setNavBarText("Noticias")
    }

    private fun setSearchViewListener() {
        noticiasSearchView = binding.noticiasSearchView

        noticiasSearchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                noticiasSearchView.clearFocus()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterData(newText, selectedTags)
                return true
            }
        })
    }


    private fun filterWithChips(selectedTags: List<String>) {
        filteredDataList = if (selectedTags.isEmpty()) {
            dataList
        } else {
            dataList.filter { noticia ->
                selectedTags.any { tag ->
                    noticia.tag.contains(tag)
                }
            }
        }
        noticiasAdapter.submitFilteredData(filteredDataList)
    }

    private fun filterData(query: String?, selectedTags: List<String>) {
        val filteredData = if (query.isNullOrBlank() && selectedTags.isEmpty()) {
            // No filtering required, return the original data list
            dataList
        } else {
            dataList.filter { noticia ->
                val matchesTag = selectedTags.isEmpty() || selectedTags.any { tag ->
                    noticia.tag.contains(tag)
                }

                val matchesQuery = query.isNullOrBlank() ||
                        noticia.title.contains(query, ignoreCase = true) ||
                        noticia.description.contains(query, ignoreCase = true)

                matchesTag && matchesQuery
            }
        }
        noticiasAdapter.submitFilteredData(filteredData)
    }


    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(noticiasSearchView.windowToken, 0)
    }

    fun getNoticias() {
        scope.launch {
            val response = viewModel.getNoticias()
        }
    }

    suspend fun getTags(): List<Tag> {
        return viewModel.getTags()
    }
}