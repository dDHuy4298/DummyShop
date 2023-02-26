package com.sun.dummyshop.di

import com.sun.dummyshop.BuildConfig
import com.sun.dummyshop.utils.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    fun initHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor {
            val original = it.request()
            val url = original.url()
                .newBuilder()
                .addQueryParameter(Constants.API, BuildConfig.API_KEY)
                .build()
            val request = original.newBuilder().url(url).build()
            it.proceed(request)
        }
        return builder.build()
    }

    fun initRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()

    single { initHttpClient() }
    single { initRetrofit(get()) }
}
