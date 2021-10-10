package com.example.challenge1

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {
    private val BASE_URL = "http://stagingapi.thehomeground.asia/api/v1/";
    fun getApiService(): HomepageApi{
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .cache(null)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(HomepageApi::class.java)
    }
}