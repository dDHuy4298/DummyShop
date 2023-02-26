package com.sun.dummyshop.data.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "keyword")
data class Keyword(
    @PrimaryKey
    @ColumnInfo
    val keyword: String
) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Keyword>() {
            override fun areItemsTheSame(oldItem: Keyword, newItem: Keyword) =
                oldItem.keyword == newItem.keyword

            override fun areContentsTheSame(oldItem: Keyword, newItem: Keyword) = oldItem == newItem
        }
    }
}
