package com.example.rudonews.domain.entity

import java.io.Serializable

data class Departament (
    val id: Number,
    val deptName: String,
    var isChecked: Boolean = false
): Serializable