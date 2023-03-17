package com.staytonight.data.model

import com.google.gson.annotations.SerializedName
import com.staytonight.domain.mapper.MapTo
import com.staytonight.domain.model.FlashSale

data class FlashSaleData(
    @SerializedName("category")
    val category: String,
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double
) : MapTo<FlashSale, FlashSaleData> {
    override fun mapTo(): FlashSale =
        FlashSale(category, discount, imageUrl, name, price)

}