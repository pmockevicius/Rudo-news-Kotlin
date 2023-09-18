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
import androidx.lifecycle.Observer
import androidx.lifecycle.distinctUntilChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.LoggedInActivity
import com.example.rudonews.data.dataSource.auth.MockDataSource
import com.example.rudonews.data.repository.DataRepository
import com.example.rudonews.databinding.FragmentNoticiasBinding
import com.example.rudonews.domain.entity.Noticia
import com.example.rudonews.domain.entity.Tag
import com.example.rudonews.domain.usecase.DataUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Noticias_fragment : Fragment() {

    private lateinit var viewModel: NoticiasViewModel
    private lateinit var dataUsecase: DataUsecase
    private lateinit var dataRepository: DataRepository
    private lateinit var mockDatasource: MockDataSource

    private lateinit var binding: FragmentNoticiasBinding
    private lateinit var noticiasSearchView: android.widget.SearchView
    private lateinit var noticiasAdapter: NoticiasAdapter
    private lateinit var dataList: List<Noticia>
    private lateinit var tagsMessage: TextView

    val scope = MainScope()
    var selectedTags: MutableList<String> = mutableListOf()

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

        scope.launch (  Dispatchers.IO){
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
//         dataList = listOf("Item 1", "Item 2", "Item 3", "Item 3", "Item 3", "Item 3") // Your data source
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
        if (selectedTags.isNotEmpty()){
            tagsMessage.visibility = View.VISIBLE
        }else {
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
            filterData(newText)
            return true
        }
    })
}

private fun filterData(query: String?) {
    val filteredData = dataList.filter { noticia ->
        // Return true if the noticia's title or description contains the query (case-insensitive)
        noticia.title.contains(query.orEmpty(), ignoreCase = true) ||
                noticia.description.contains(query.orEmpty(), ignoreCase = true)
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