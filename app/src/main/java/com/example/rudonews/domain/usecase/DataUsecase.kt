package com.example.rudonews.domain.usecase


import com.example.rudonews.data.repository.DataRepository
import com.example.rudonews.domain.entity.Departament


interface DataUsecaseInterface {
    fun getDepartments(): List<Departament>
}
class DataUsecase(val dataRepository: DataRepository): DataUsecaseInterface {
    override fun getDepartments(): List<Departament> {
        return dataRepository.getDepartments()
    }


}