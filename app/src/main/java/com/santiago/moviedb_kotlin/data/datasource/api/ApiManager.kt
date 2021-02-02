package com.santiago.moviedb_kotlin.data.datasource.api

import com.google.gson.GsonBuilder
import com.santiago.moviedb_kotlin.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiManager {

    var retrofit: Retrofit

    init {
        retrofit = generateRetrofit()
    }

    private fun generateRetrofit() : Retrofit {
        val gson = GsonBuilder().setLenient().create()
        val client = getOkHttpClient()

        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.readTimeout(1, TimeUnit.MINUTES)
        builder.connectTimeout(1, TimeUnit.MINUTES)
        builder.addInterceptor(interceptor)
        builder.addInterceptor(Interceptor {
            val original = it.request()
            val requestBuilder = original.newBuilder()
                    .addHeader("Content-Type","application/json")
                    .method(original.method, original.body)
            return@Interceptor it.proceed(requestBuilder.build())
        })
        return builder.build()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}