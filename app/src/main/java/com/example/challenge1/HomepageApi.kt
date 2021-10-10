package com.example.challenge1

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface HomepageApi {
    @Headers("Authorization: Basic dGhnYWRtaW46dGhncGFzc3dvcmQ=")
    @POST("category_articles")
    suspend fun artical(@Body request: JsonObject): Response<ResponseModel>
} 