package com.staytonight.domain.storage

import com.staytonight.domain.model.User

interface DataStorage {
    fun login(email: String, password: String): Boolean
    fun register(firstName: String, lastName: String, email: String): Boolean
    fun getCurrentUser(): User?
    fun logout()
    fun setAvatar(avatar: String)
}