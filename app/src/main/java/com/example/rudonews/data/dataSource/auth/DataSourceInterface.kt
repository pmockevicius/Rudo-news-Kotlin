package com.example.rudonews.data.dataSource.auth

import com.example.rudonews.domain.entity.Departament
import com.example.rudonews.domain.entity.Noticia
import com.example.rudonews.domain.entity.Tag

interface DataSourceInterface {
    suspend fun getDepartments(): List<Departament>

    suspend fun getNoticias(): List<Noticia>

    suspend fun getTags(): List<Tag>

}