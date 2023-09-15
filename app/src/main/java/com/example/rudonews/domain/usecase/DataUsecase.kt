package com.example.rudonews.domain.usecase


import com.example.rudonews.data.repository.DataRepository
import com.example.rudonews.domain.entity.Departament
import com.example.rudonews.domain.entity.Noticia


interface DataUsecaseInterface {
    suspend fun getDepartments(): List<Departament>
    suspend fun getNoticias(): List<Noticia>
}
class DataUsecase(val dataRepository: DataRepository): DataUsecaseInterface {
    override suspend fun getDepartments(): List<Departament> {
        return dataRepository.getDepartments()
    }

    override suspend fun getNoticias(): List<Noticia> {
        return dataRepository.getNoticias()
    }

}