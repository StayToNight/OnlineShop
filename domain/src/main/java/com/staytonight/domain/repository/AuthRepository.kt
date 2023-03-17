package com.staytonight.domain.repository

import com.staytonight.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun register(firstName: String, lastName: String, email: String): Boolean
    suspend fun getUserData(): User?
    suspend fun logout()
    suspend fun changeAvatar(avatar: String)
}