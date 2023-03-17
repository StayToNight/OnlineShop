package com.staytonight.data.repository

import com.staytonight.domain.model.User
import com.staytonight.domain.repository.AuthRepository
import com.staytonight.domain.storage.DataStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val storage: DataStorage
) : AuthRepository {

    override suspend fun login(email: String, password: String): Boolean =
        withContext(Dispatchers.IO) {
            storage.login(email, password)
        }

    override suspend fun register(firstName: String, lastName: String, email: String): Boolean =
        withContext(Dispatchers.IO) {
            storage.register(firstName, lastName, email)
        }

    override suspend fun getUserData(): User? =
        withContext(Dispatchers.IO) {
            storage.getCurrentUser()
        }

    override suspend fun logout() =
        withContext(Dispatchers.IO) {
            storage.logout()
        }

    override suspend fun changeAvatar(avatar: String) =
        withContext(Dispatchers.IO) {
            storage.setAvatar(avatar)
        }
}