package com.example.rudonews.data.repository

import com.example.rudonews.data.dataSource.auth.MockDataSource
import com.example.rudonews.domain.entity.Departament
import com.example.rudonews.domain.entity.Noticia
import com.example.rudonews.domain.entity.Tag


interface DataRepositoryInterface  {

    suspend fun getDepartments(): List<Departament>
   suspend fun getNoticias(): List<Noticia>
   suspend fun getTags(): List<Tag>

}
class DataRepository(private val mockDataSource: MockDataSource) :DataRepositoryInterface {
    override suspend fun getDepartments(): List<Departament> {
       var departments = mockDataSource.getDepartments()
        return departments
    }

    override suspend fun getNoticias(): List<Noticia> {

        val noticias = mockDataSource.getNoticias()
        return noticias
    }

    override suspend fun getTags(): List<Tag> {
        return  mockDataSource.getTags()
    }


}