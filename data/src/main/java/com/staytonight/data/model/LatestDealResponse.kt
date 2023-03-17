package com.staytonight.data.model

import com.google.gson.annotations.SerializedName

data class LatestDealResponse(
    @SerializedName("latest")
    val latest: List<LatestData>
)