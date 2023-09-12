package com.example.rudonews.domain.entity

data class Departament (
    val id: Number,
    val deptName: String,
    var isChecked: Boolean = false
)