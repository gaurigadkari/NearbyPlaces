package com.example.nearbyplaces.ui.theme

/**
 * Created by Gauri Gadkari on 11/9/23.
 */
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class NetworkInterceptor : Interceptor {
    private val logger = HttpLoggingInterceptor.Logger { message -> println(message) }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        logger.log("Request URL: ${request.url}")
        return chain.proceed(request)
    }
}