package com.example.rudonews.presentation.departamentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.MainActivity
import com.example.rudonews.R
import com.example.rudonews.data.dataSource.auth.MockAuthDatasource
import com.example.rudonews.data.dataSource.auth.MockDataSource
import com.example.rudonews.data.repository.AuthRepository
import com.example.rudonews.data.repository.DataRepository
import com.example.rudonews.databinding.FragmentDepartamentosBinding
import com.example.rudonews.databinding.RegisterFragmentBinding
import com.example.rudonews.domain.entity.Departament
import com.example.rudonews.domain.usecase.AuthUsecase
import com.example.rudonews.domain.usecase.DataUsecase
import com.example.rudonews.presentation.login.LoginViewModel


class Departamentos_Fragment : Fragment() {

private lateinit var viewModel: DepartamentosViewModel
    private lateinit var binding: FragmentDepartamentosBinding


    private lateinit var dataUsecase: DataUsecase
    private lateinit var dataRepository: DataRepository
    private lateinit var mockDatasource: MockDataSource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = activity as? MainActivity
        activity?.setNavBarText("Departamentos")

        mockDatasource = MockDataSource()
        dataRepository = DataRepository(mockDatasource)
        dataUsecase = DataUsecase(dataRepository)
        viewModel = DepartamentosViewModel(dataUsecase)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDepartamentosBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView()

    }

    fun setupRecyclerView(){
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewDepartamentos)
        if (recyclerView != null) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        val departments = getDepartments()

        val adapter = DepartamentoAdapter(departments)
        if (recyclerView != null) {
            recyclerView.adapter = adapter
        }

        displayDepartments()
    }

    fun getDepartments(): List<Departament> {
        var departaments = viewModel.getDepartaments()
        return departaments
    }


    fun displayDepartments(){
//        val departmentContainer = binding.departmentContainer
//        val departments = getDepartments()
//
//        for (department in departments) {
//            val checkBox = CheckBox(requireContext())
//
//            checkBox.text = department.deptName
//            checkBox.layoutParams = ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//
//            checkBox.setTextColor(ContextCompat.getColor(requireContext(), R.color.fucia))
//            departmentContainer.addView(checkBox)
//        }


    }

}