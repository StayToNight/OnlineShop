package com.staytonight.domain.usecase.details

import com.staytonight.domain.repository.DetailsRepository
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val detailsRepository: DetailsRepository
) {
    suspend fun getDetails() = detailsRepository.getDetails()
}