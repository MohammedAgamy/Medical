package com.example.medical.client

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptorprivate(val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token") // Attach Bearer Token
            .build()
        return chain.proceed(request)
    }
}