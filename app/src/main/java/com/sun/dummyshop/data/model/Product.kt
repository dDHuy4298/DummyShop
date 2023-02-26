package com.sun.dummyshop.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "product")
data class Product(
    @PrimaryKey
    @SerializedName("_id")
    @ColumnInfo(name = "id")
    val id: String,
    @SerializedName("product_image_lg")
    @ColumnInfo(name = "image")
    val image: String,
    @SerializedName("product_name")
    @ColumnInfo(name = "name")
    val name: String,
//    @SerializedName("product_department")
//    @ColumnInfo(name = "category")
//    val category: String,
//    @SerializedName("product_departmentId")
//    @ColumnInfo(name = "categoryId")
//    val categoryId: String,
    @SerializedName("product_stock")
    @ColumnInfo(name = "stock")
    val stock: Int,
    @SerializedName("product_color")
    @ColumnInfo(name = "color")
    val color: String?,
    @SerializedName("product_price")
    @ColumnInfo(name = "price")
    val price: String,
    @SerializedName("product_material")
    @ColumnInfo(name = "material")
    val material: String?,
    @SerializedName("product_ratings")
    @ColumnInfo(name = "rating")
    val rating: Int,
    @SerializedName("product_sales")
    @ColumnInfo(name = "sold")
    val sold: Int,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Int,
    @ColumnInfo(name = "quantityInCart")
    val quantityInCart: Int
) : Parcelable {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
        }
    }
}
