package com.sun.dummyshop.di

import androidx.room.Room
import com.sun.dummyshop.data.source.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }
    single { get<AppDatabase>().productDao() }
    single { get<AppDatabase>().billDao() }
    single { get<AppDatabase>().keywordDao() }
}
