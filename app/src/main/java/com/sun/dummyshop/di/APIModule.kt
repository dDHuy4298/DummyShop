package com.sun.dummyshop.di

import com.sun.dummyshop.data.source.remote.Api
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(Api::class.java) }
}
