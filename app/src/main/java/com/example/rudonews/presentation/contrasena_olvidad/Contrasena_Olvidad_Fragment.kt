package com.example.rudonews.presentation.contrasena_olvidad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rudonews.activities.MainActivity
import com.example.rudonews.data.dataSource.auth.MockAuthDatasource
import com.example.rudonews.data.repository.AuthRepository
import com.example.rudonews.databinding.FragmentContrasenaOlvidadBinding
import com.example.rudonews.domain.usecase.AuthUsecase
import com.example.rudonews.utils.helpers.DialogHelper
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class Contrasena_Olvidad_Fragment : Fragment() {

    private lateinit var binding: FragmentContrasenaOlvidadBinding
    private val scope = MainScope()

    private lateinit var viewModel: OlvidadViewModel
    private lateinit var authUsecase: AuthUsecase
    private lateinit var authRepository: AuthRepository
    private lateinit var mockAuthDatasource: MockAuthDatasource



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mockAuthDatasource = MockAuthDatasource()
        authRepository = AuthRepository(mockAuthDatasource)
        authUsecase = AuthUsecase(authRepository)
        viewModel = OlvidadViewModel(authUsecase)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContrasenaOlvidadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onView()
    }

    private fun onView(){
        setNavBarTitle()
        initBtnClickListener()

    }

    private fun initBtnClickListener(){

        binding.btnReestablecer.setOnClickListener{
            var inputText = binding.textInputMailOlvidad.text.toString()

            println("inputText $inputText")

            scope.launch{
                var responseMessage = viewModel.resetPassword(inputText)
                showDialog(responseMessage)
            }

        }
    }

    private fun setNavBarTitle(){
        val activity = activity as? MainActivity
        activity?.setNavBarText("Contraseña olvidada")
    }

    private fun showDialog(message: String){
        DialogHelper.showAlertDialog(requireContext(), message)
    }

}