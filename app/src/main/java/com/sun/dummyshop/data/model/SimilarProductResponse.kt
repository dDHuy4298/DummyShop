package com.sun.dummyshop.data.model

import com.google.gson.annotations.SerializedName

data class SimilarProductResponse(
    @SerializedName("data")
    val data: SimilarProductData
)
