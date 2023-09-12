package com.example.rudonews.presentation.departamentos

import com.example.rudonews.domain.entity.Departament
import com.example.rudonews.domain.usecase.AuthUsecase
import com.example.rudonews.domain.usecase.DataUsecase


interface DepartamentosViewModelInterface{
    fun getDepartaments(): List<Departament>
}

class DepartamentosViewModel(val dataUsecase: DataUsecase) : DepartamentosViewModelInterface {
    override fun getDepartaments(): List<Departament> {
        return dataUsecase.getDepartments()
    }


}