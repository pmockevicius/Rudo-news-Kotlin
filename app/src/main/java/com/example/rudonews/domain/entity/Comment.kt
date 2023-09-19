package com.example.rudonews.domain.entity

import java.io.Serializable

data class Comment (
    val owner: String,
    val departaments: String,
    val datePosted: String,
    val userImage: String,
    val body: String,
): Serializable
