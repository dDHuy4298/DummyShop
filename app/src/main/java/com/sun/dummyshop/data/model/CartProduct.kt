package com.sun.dummyshop.data.model

import com.google.gson.annotations.SerializedName

data class CartProduct(
    @SerializedName("_id")
    val id: String,
    @SerializedName("product_quantity")
    val quantity: String
)
