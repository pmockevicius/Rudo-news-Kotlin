package com.example.rudonews.domain.usecase

import com.example.rudonews.data.repository.AuthRepository
import com.example.rudonews.domain.entity.User

interface AuthUsecaseInterface{
    suspend fun loginUser()
    suspend fun checkLoginCredentials(mail:String, password: String):Boolean
}

class AuthUsecase(val authRepository: AuthRepository): AuthUsecaseInterface {
    override suspend fun loginUser() {
        println("We are in usecase")
        authRepository.getUsers()
    }

    override suspend fun checkLoginCredentials(mail: String, password: String): Boolean {
        val users: List<User> = authRepository.getUsers()

        var userExists = false

        users.forEach { user ->
            if (user.email == mail && user.password == password) {
                userExists = true
                return@forEach
            }
        }

        return userExists
    }


}