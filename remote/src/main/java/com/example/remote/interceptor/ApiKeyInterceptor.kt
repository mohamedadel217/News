package com.example.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("X-Api-Key", "a6d71b94eac54d108b1953415239d4ff")
        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }

}