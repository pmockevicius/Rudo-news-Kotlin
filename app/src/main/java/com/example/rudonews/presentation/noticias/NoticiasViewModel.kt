package com.example.rudonews.presentation.noticias

import com.example.rudonews.domain.entity.News
import com.example.rudonews.domain.entity.Tag
import com.example.rudonews.domain.usecase.DataUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface NoticiasViewModelInterface{
     suspend fun getNoticias(): List<News>
     suspend fun getTags(): List<Tag>

}

class NoticiasViewModel(val dataUsecase: DataUsecase) : NoticiasViewModelInterface {

//    var news = MutableLiveData<List<Noticia>>()
    override suspend fun getNoticias(): List<News> {
    var response=  (emptyList <News>())

    withContext(Dispatchers.IO) {
        response = dataUsecase.getNoticias()
    }
    println("response in view model ${response.size}")
    return response

    }

    override suspend fun getTags(): List<Tag> {
        var response = (emptyList <Tag>())

        withContext(Dispatchers.IO){
            response = dataUsecase.getTags()
        }
        return response
    }


}