package com.staytonight.domain.usecase.home

import com.staytonight.domain.repository.HomeRepository
import javax.inject.Inject

class GetFlashSaleUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend fun getFlashSale() = homeRepository.getFlashSales()
}