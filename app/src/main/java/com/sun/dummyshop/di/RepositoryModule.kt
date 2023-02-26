package com.sun.dummyshop.di

import com.sun.dummyshop.data.repository.*
import com.sun.dummyshop.data.source.BillDataSource
import com.sun.dummyshop.data.source.CategoryDataSource
import com.sun.dummyshop.data.source.KeywordDataSource
import com.sun.dummyshop.data.source.ProductDataSource
import com.sun.dummyshop.data.source.local.BillLocalDataSource
import com.sun.dummyshop.data.source.local.KeywordLocalDataSource
import com.sun.dummyshop.data.source.local.ProductLocalDataSource
import com.sun.dummyshop.data.source.remote.BillRemoteDataSource
import com.sun.dummyshop.data.source.remote.CategoryRemoteDataSource
import com.sun.dummyshop.data.source.remote.ProductRemoteDataSource
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductDataSource.Local> { ProductLocalDataSource(get()) }
    single<ProductDataSource.Remote> { ProductRemoteDataSource(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get(), get()) }
    single<CategoryDataSource> { CategoryRemoteDataSource(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<BillDataSource.Local> { BillLocalDataSource(get()) }
    single<BillDataSource.Remote> { BillRemoteDataSource(get()) }
    single<BillRepository> { BillRepositoryImpl(get(), get()) }
    single<KeywordDataSource> { KeywordLocalDataSource(get()) }
    single<KeywordRepository> { KeywordRepositoryImpl(get()) }
}
