package com.example.rudonews.presentation.noticias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rudonews.domain.entity.Noticia
import com.example.rudonews.domain.usecase.DataUsecase

interface NoticiasViewModelInterface{
    suspend fun getNoticias(): List<Noticia>
}

class NoticiasViewModel(val dataUsecase: DataUsecase) : NoticiasViewModelInterface {

    var news = MutableLiveData<List<Noticia>>()
    override suspend fun getNoticias(): List<Noticia> {
        val response = dataUsecase.getNoticias()
        println("response in view model $response")
        news.value = response
        return response
    }


}