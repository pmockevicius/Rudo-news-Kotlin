package com.example.rudonews.presentation.departamentos

import com.example.rudonews.domain.entity.Departament
import com.example.rudonews.domain.usecase.AuthUsecase
import com.example.rudonews.domain.usecase.DataUsecase


interface DepartamentosViewModelInterface{
    suspend fun getDepartaments(): List<Departament>
}

class DepartamentosViewModel(val dataUsecase: DataUsecase) : DepartamentosViewModelInterface {
    override suspend fun getDepartaments(): List<Departament> {
        return dataUsecase.getDepartments()
    }


}