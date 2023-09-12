package com.example.rudonews.presentation.departamentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rudonews.MainActivity
import com.example.rudonews.R
import com.example.rudonews.data.dataSource.auth.MockDataSource
import com.example.rudonews.data.repository.DataRepository
import com.example.rudonews.databinding.FragmentDepartamentosBinding
import com.example.rudonews.domain.entity.Departament
import com.example.rudonews.domain.usecase.DataUsecase
import com.example.rudonews.presentation.departamentos.DepartamentoViewHolder
import com.example.rudonews.presentation.register.Register_fragment


class Departamentos_Fragment : Fragment() {

    private lateinit var viewModel: DepartamentosViewModel
    private lateinit var binding: FragmentDepartamentosBinding

    private lateinit var departments: List<Departament>


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
        initDepAcceptBtnListener()
    }

    fun initDepAcceptBtnListener(){
        binding.btnDepartamento.setOnClickListener{

            var selectedDepartments = getSelectedDepartments()
            println("accept pressed")
            println("selectedDepartments $selectedDepartments")
            val registerFragment = Register_fragment()
            val bundle = Bundle()
            bundle.putSerializable("selectedDepartments", ArrayList(selectedDepartments))

            registerFragment.arguments = bundle

            println("${registerFragment.arguments} arguments")

            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, registerFragment)
                addToBackStack(null)
                commit()
            }
        }
    }

    fun setupRecyclerView() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewDepartamentos)
        if (recyclerView != null) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
         departments = getDepartments()

        val adapter = DepartamentoAdapter(departments)
        if (recyclerView != null) {
            recyclerView.adapter = adapter
        }

    }

    fun getDepartments(): List<Departament> {
         var departments = viewModel.getDepartaments()
        println("departamentos $departments")
        return departments
    }

    fun getSelectedDepartments(): List<Departament> {
        val selectedDepartments = mutableListOf<Departament>()

        for (department in departments) {
            if (department.isChecked) {
                selectedDepartments.add(department)
            }
        }

        return selectedDepartments
    }

}

