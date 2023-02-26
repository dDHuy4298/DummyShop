package com.sun.dummyshop.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BillProduct(
    @SerializedName("_id")
    val id: String,
    @SerializedName("product_name")
    val name: String,
    @SerializedName("product_price")
    val price: String,
    @SerializedName("product_quantity")
    val quantity: Int,
    @SerializedName("product_image_lg")
    val image: String,
) : Parcelable {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<BillProduct>() {
            override fun areItemsTheSame(oldItem: BillProduct, newItem: BillProduct) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BillProduct, newItem: BillProduct) = oldItem == newItem
        }
    }
}
