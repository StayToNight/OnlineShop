package com.staytonight.domain.usecase.auth

import com.staytonight.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun register(firstName: String, lastName: String, email: String) =
        authRepository.register(firstName, lastName, email)
}