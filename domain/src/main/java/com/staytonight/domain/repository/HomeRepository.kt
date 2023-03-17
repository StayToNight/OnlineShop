package com.staytonight.domain.repository

import com.staytonight.domain.model.FlashSale
import com.staytonight.domain.model.Latest

interface HomeRepository {
    suspend fun getLatestDeal(): List<Latest>
    suspend fun getFlashSales(): List<FlashSale>
}