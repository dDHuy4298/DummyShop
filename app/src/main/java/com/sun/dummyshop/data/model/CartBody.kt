package com.sun.dummyshop.data.model

import com.google.gson.annotations.SerializedName

data class CartBody(
    @SerializedName("items")
    val products: List<CartProduct>
)
