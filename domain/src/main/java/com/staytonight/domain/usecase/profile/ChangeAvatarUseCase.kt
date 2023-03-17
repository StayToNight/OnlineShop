package com.staytonight.domain.usecase.profile

import com.staytonight.domain.repository.AuthRepository
import javax.inject.Inject

class ChangeAvatarUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun changeAvatar(avatar: String) = authRepository.changeAvatar(avatar)
}