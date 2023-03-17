package com.staytonight.data.repository

import com.staytonight.data.network.Api
import com.staytonight.domain.model.Details
import com.staytonight.domain.repository.DetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val api: Api
) : DetailsRepository {

    override suspend fun getDetails(): Details =
        withContext(Dispatchers.IO) {
            val response = api.getDetails()
            if (response.isSuccessful)
                response.body()?.mapTo() ?: throw Throwable(response.message())
            else
                throw Throwable(response.errorBody().toString())
        }

}