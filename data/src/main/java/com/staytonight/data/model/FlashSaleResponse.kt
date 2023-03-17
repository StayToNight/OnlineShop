package com.staytonight.data.model

import com.google.gson.annotations.SerializedName

data class FlashSaleResponse(
    @SerializedName("flash_sale")
    val flashSale: List<FlashSaleData>
)