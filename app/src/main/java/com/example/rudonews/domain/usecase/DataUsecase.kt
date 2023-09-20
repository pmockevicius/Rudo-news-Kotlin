package com.example.rudonews.domain.usecase


import com.example.rudonews.data.repository.DataRepository
import com.example.rudonews.domain.entity.Departament
import com.example.rudonews.domain.entity.News
import com.example.rudonews.domain.entity.Tag


interface DataUsecaseInterface {
    suspend fun getDepartments(): List<Departament>
    suspend fun getNoticias(): List<News>

    suspend fun getTags(): List<Tag>
}
class DataUsecase(val dataRepository: DataRepository): DataUsecaseInterface {
    override suspend fun getDepartments(): List<Departament> {
        return dataRepository.getDepartments()
    }

    override suspend fun getNoticias(): List<News> {
        return dataRepository.getNoticias()
    }

    override suspend fun getTags(): List<Tag> {
        return dataRepository.getTags()
    }

}