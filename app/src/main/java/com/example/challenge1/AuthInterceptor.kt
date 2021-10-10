package com.example.challenge1

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest: Request = request.newBuilder()
                    .header("Accept", "*/*")
                    .header("Content-Type", "text/plain")
                    .header("Authorization", "Basic dGhnYWRtaW46dGhncGFzc3dvcmQ=")
                    .build()

        return chain.proceed(authenticatedRequest)
    }
}