package com.staytonight.data.model

import com.google.gson.annotations.SerializedName
import com.staytonight.domain.mapper.MapTo
import com.staytonight.domain.model.Latest

data class LatestData(
    @SerializedName("category")
    val category: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
) : MapTo<Latest, LatestData> {
    override fun mapTo(): Latest =
        Latest(
            category, imageUrl, name, price
        )
}