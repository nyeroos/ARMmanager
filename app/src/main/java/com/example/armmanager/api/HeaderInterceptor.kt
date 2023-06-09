package com.example.armmanager.api

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val prefs: SharedPreferences?) : Interceptor {

    val token: String? get() = prefs?.getString("token", "")

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = token.takeUnless { it.isNullOrEmpty() }?.let {
                request.newBuilder().addHeader("Authorization", "Bearer $it").build()
            } ?: request
        return chain.proceed(request)
    }

}