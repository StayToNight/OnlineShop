package com.staytonight.domain.usecase.profile

import com.staytonight.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun logout() = authRepository.logout()
}