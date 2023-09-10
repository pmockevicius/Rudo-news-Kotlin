package com.example.rudonews.presentation.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.rudonews.MainActivity
import com.example.rudonews.R
import com.example.rudonews.data.dataSource.auth.MockAuthDatasource
import com.example.rudonews.data.repository.AuthRepository
import com.example.rudonews.domain.usecase.AuthUsecase
import com.example.rudonews.presentation.register.Register_fragment
import com.example.rudonews.utils.helpers.DialogHelper.Companion.showAlertDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class Login_fragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var authUsecase: AuthUsecase
    private lateinit var authRepository: AuthRepository
    private lateinit var mockAuthDatasource: MockAuthDatasource

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mockAuthDatasource = MockAuthDatasource()
        authRepository = AuthRepository(mockAuthDatasource)
        authUsecase = AuthUsecase(authRepository)
        viewModel = LoginViewModel(authUsecase)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailEditText = view.findViewById(R.id.textInputMail)
        passwordEditText = view.findViewById(R.id.textInputPassword)
        loginButton = view.findViewById(R.id.BtnLogin)
        registerButton = view.findViewById(R.id.BtnRegister)



        onView()
        initTextChangeListeners()
        initLoginClickListener()
        initRegisterClickListener()
    }

    private fun initRegisterClickListener() {
        registerButton.setOnClickListener {
            (activity as? MainActivity)?.navigateToRegisterPage()
        }
    }

    private fun initLoginClickListener() {
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            val response = GlobalScope.async(Dispatchers.IO) {
                viewModel.checkLoginCredentials(email, password)
            }

            GlobalScope.launch(Dispatchers.Main) {
                val result = response.await()
                if (result) {
                    showAlertDialog(requireContext(), "Email and password are correct, LOGGING IN")
                } else {
                    showAlertDialog(requireContext(), "Wrong credentials, try again")
                }
            }
        }
    }

    private fun initTextChangeListeners() {

        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (emailEditText.text.isNullOrBlank() || passwordEditText.text.isNullOrBlank()) {
                    loginButton.isEnabled = false
                    loginButton.setBackgroundColor(resources.getColor(R.color.fucia_disabled))
                } else {
                    loginButton.isEnabled = true
                    loginButton.setBackgroundColor(resources.getColor(R.color.fucia))
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (emailEditText.text.isNullOrBlank() || passwordEditText.text.isNullOrBlank()) {
                    loginButton.isEnabled = false
                    loginButton.setBackgroundColor(resources.getColor(R.color.fucia_disabled))
                } else {
                    loginButton.isEnabled = true
                    loginButton.setBackgroundColor(resources.getColor(R.color.fucia))
                }
            }
        })
    }

    fun onView() {
        loginButton.isEnabled = false
        loginButton.setBackgroundColor(resources.getColor(R.color.fucia_disabled))

        val activity = activity as? MainActivity
        activity?.setNavBarText("Iniciar sesión")
    }

}