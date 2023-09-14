package com.example.rudonews.presentation.login

import com.example.rudonews.domain.usecase.AuthUsecase


interface LoginViewModelInterface {
suspend fun logingUser(mail:String, password: String) :Boolean
}

class LoginViewModel(val usecase: AuthUsecase) : LoginViewModelInterface{

    override suspend fun logingUser(mail:String, password: String) :Boolean{
        var response = usecase.checkLoginCredentials(mail, password)

        return response


//        return if (response) {
//            "Credentials are correct, Logging in!!!"
//        } else


    }




}