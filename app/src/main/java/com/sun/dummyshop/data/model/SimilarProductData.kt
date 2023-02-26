package com.sun.dummyshop.data.model

import com.google.gson.annotations.SerializedName

data class SimilarProductData(
    @SerializedName("product_similar")
    val items: List<Product>
)
