package com.example.rudonews.data.repository

import com.example.rudonews.data.dataSource.auth.MockDataSource
import com.example.rudonews.domain.entity.Departament


interface DataRepositoryInterface  {

    fun getDepartments(): List<Departament>

}
class DataRepository(private val mockDataSource: MockDataSource) :DataRepositoryInterface {
    override fun getDepartments(): List<Departament> {
       var departments = mockDataSource.getMockDepartments()
        return departments
    }


}