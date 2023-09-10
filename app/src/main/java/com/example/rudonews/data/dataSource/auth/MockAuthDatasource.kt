package com.example.rudonews.data.dataSource.auth

import com.example.rudonews.data.repository.AuthRepository
import com.example.rudonews.domain.entity.User

class MockAuthDatasource() : AuthDatasourceInterface {

    override fun getMockUsers(): List<User> {
        println("getting users from mock")

        val users = mutableListOf<User>()

        users.add(User("test", "test"))
        users.add(User("user2@example.com", "password2"))
        users.add(User("user3@example.com", "password3"))
        users.add(User("user4@example.com", "password4"))

        return users
    }
}