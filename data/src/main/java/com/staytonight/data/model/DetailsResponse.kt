package com.staytonight.data.model

import com.google.gson.annotations.SerializedName
import com.staytonight.domain.mapper.MapTo
import com.staytonight.domain.model.Details

data class DetailsResponse(
    @SerializedName("colors")
    val colors: List<String>,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("number_of_reviews")
    val numberOfReviews: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("rating")
    val rating: Double
) : MapTo<Details, DetailsResponse> {
    override fun mapTo(): Details =
        Details(colors, description, imageUrls, name, numberOfReviews, price, rating)

}