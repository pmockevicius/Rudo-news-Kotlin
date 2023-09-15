package com.example.rudonews.data.repository

import com.example.rudonews.data.dataSource.auth.MockAuthDatasource
import com.example.rudonews.domain.entity.User

interface AuthRepositoryInterface{

    suspend fun getUsers(): List<User>

}

class AuthRepository(private val mockAuthDatasource: MockAuthDatasource):AuthRepositoryInterface  {

    override suspend fun getUsers(): List<User> {
        var users  = mockAuthDatasource.getUsers()
        return users
    }

}