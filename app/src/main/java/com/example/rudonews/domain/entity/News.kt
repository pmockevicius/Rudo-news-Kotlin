package com.example.rudonews.domain.entity

import java.io.Serializable

class News (
    val image: String,
    val title: String,
    val date: String,
    val description: String,
    val tag: String,
    val body: String,
    var isFavorite: Boolean,
    var id: Number,
    var comments: List<Comment>?
) : Serializable