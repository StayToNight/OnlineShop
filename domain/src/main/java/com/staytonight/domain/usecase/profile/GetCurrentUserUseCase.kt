package com.staytonight.domain.usecase.profile

import com.staytonight.domain.storage.DataStorage
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val dataStorage: DataStorage
) {
    fun getCurrentUser() = dataStorage.getCurrentUser()
}