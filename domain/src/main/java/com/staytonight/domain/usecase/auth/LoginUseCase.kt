package com.staytonight.domain.usecase.auth

import com.staytonight.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun login(email: String, password: String) = authRepository.login(email, password)
}