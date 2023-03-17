package com.staytonight.domain.repository

import com.staytonight.domain.model.Details

interface DetailsRepository {
    suspend fun getDetails(): Details
}