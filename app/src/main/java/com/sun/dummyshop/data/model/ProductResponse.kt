package com.sun.dummyshop.data.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("data")
    val data: List<Product>
)
