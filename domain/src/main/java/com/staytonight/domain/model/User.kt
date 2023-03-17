package com.staytonight.domain.model

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val balance: Int,
    var avatar: String? = null
)