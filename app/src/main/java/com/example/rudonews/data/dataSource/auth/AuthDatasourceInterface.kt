package com.example.rudonews.data.dataSource.auth

import com.example.rudonews.domain.entity.User

interface AuthDatasourceInterface {

    fun getUsers():List<User>
}