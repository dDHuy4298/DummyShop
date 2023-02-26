package com.sun.dummyshop.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sun.dummyshop.data.model.Bill
import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.data.model.Keyword
import com.sun.dummyshop.data.source.local.AppDatabase.Companion.DATABASE_VERSION
import com.sun.dummyshop.data.source.local.AppDatabase.Companion.EXPORT_SCHEME
import com.sun.dummyshop.data.source.local.dao.BillDao
import com.sun.dummyshop.data.source.local.dao.KeywordDao
import com.sun.dummyshop.data.source.local.dao.ProductDao
import com.sun.dummyshop.utils.CartItemTypeConverter

@Database(
    entities = [Product::class, Bill::class, Keyword::class],
    version = DATABASE_VERSION,
    exportSchema = EXPORT_SCHEME
)

@TypeConverters(CartItemTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun billDao(): BillDao

    abstract fun keywordDao(): KeywordDao

    companion object {
        const val DATABASE_NAME = "DummyShopDb"
        const val DATABASE_VERSION = 1
        const val EXPORT_SCHEME = false
    }
}
