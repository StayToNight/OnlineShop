package com.staytonight.data.repository

import com.staytonight.data.network.Api
import com.staytonight.domain.model.FlashSale
import com.staytonight.domain.model.Latest
import com.staytonight.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: Api
) : HomeRepository {
    override suspend fun getLatestDeal(): List<Latest> = withContext(Dispatchers.IO) {
        val response = api.getLastDeal()
        if (response.isSuccessful) {
            response.body()?.latest?.map { it.mapTo() } ?: emptyList()
        } else
            throw Throwable(response.code().toString())
    }

    override suspend fun getFlashSales(): List<FlashSale> = withContext(Dispatchers.IO) {
        val response = api.getFlashSale()
        if (response.isSuccessful) {
            response.body()?.flashSale?.map { it.mapTo() } ?: emptyList()
        } else
            throw Throwable(response.errorBody().toString())
    }
}