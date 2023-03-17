package com.staytonight.domain.usecase.home

import com.staytonight.domain.repository.HomeRepository
import javax.inject.Inject

class GetLatestDealUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend fun getLatestDeal() = homeRepository.getLatestDeal()
}