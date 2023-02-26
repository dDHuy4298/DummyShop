package com.sun.dummyshop.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sun.dummyshop.data.model.BillProduct
import java.io.Serializable

class CartItemTypeConverter : Serializable {

    @TypeConverter
    fun cartItemsToJson(billProducts: List<BillProduct>): String {
        val type = object : TypeToken<List<BillProduct>>() {}.type
        return Gson().toJson(billProducts, type)
    }

    @TypeConverter
    fun jsonToCartItems(json: String): List<BillProduct> {
        val type = object : TypeToken<List<BillProduct>>() {}.type
        return Gson().fromJson(json, type)
    }
}
