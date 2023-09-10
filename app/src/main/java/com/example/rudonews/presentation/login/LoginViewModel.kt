package com.example.rudonews.presentation.login

import com.example.rudonews.domain.usecase.AuthUsecase


interface LoginViewModelInterface {
    suspend fun checkLoginCredentials(mail: String, password: String): Boolean
}

class LoginViewModel(val usecase: AuthUsecase) : LoginViewModelInterface{

    override suspend fun checkLoginCredentials(mail:String, password: String) :Boolean{

        return usecase.checkLoginCredentials(mail, password)

    }


}