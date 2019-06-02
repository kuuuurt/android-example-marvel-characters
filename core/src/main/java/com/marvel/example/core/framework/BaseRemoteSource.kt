package com.marvel.example.core.framework

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.marvel.example.core.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 19/04/2019
 */
abstract class BaseRemoteSource<Api>(clazz: Class<Api>) {
    private val baseUrl = BuildConfig.MARVEL_API_BASE_URL
    private val moshi = Moshi.Builder().build()

    private var retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .build()

    private val okHttpClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }

            builder.addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()

                val publicKey = BuildConfig.MARVEL_API_PUBLIC_KEY
                val hash = BuildConfig.MARVEL_API_HASH

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("ts", "testappmodularization")
                    .addQueryParameter("apikey", publicKey)
                    .addQueryParameter("hash", hash)
                    .build()

                val requestBuilder = original.newBuilder().url(url)

                chain.proceed(requestBuilder.build())
            }

            return builder.build()
        }

    var api: Api = retrofit.create<Api>(clazz)
}