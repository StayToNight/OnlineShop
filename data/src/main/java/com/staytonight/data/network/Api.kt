package com.staytonight.data.network

import com.staytonight.data.model.DetailsResponse
import com.staytonight.data.model.FlashSaleResponse
import com.staytonight.data.model.LatestDealResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7/")
    suspend fun getLastDeal(): Response<LatestDealResponse>

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac/")
    suspend fun getFlashSale(): Response<FlashSaleResponse>

    @GET("f7f99d04-4971-45d5-92e0-70333383c239/")
    suspend fun getDetails(): Response<DetailsResponse>
}