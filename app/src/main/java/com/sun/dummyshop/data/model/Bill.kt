package com.sun.dummyshop.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "bill")
data class Bill(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("date")
    @ColumnInfo(name = "date")
    val date: String,
    @SerializedName("totalItems")
    @ColumnInfo(name = "totalItems")
    val totalItems: Int,
    @SerializedName("totalAmount")
    @ColumnInfo(name = "totalAmount")
    val totalAmount: Int,
    @SerializedName("itemsBought")
    @ColumnInfo(name = "itemsBought")
    val itemsBought: List<BillProduct>?,
) : Parcelable {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Bill>() {
            override fun areItemsTheSame(oldItem: Bill, newItem: Bill) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Bill, newItem: Bill) = oldItem == newItem
        }
    }
}
