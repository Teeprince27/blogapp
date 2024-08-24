package com.temitope.blogwebsiteapplication.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BlogCloud {
    companion object {
        private val blogCloud by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            //Set custom timeouts
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(60, TimeUnit.SECONDS) // Increase read timeout
                .writeTimeout(60, TimeUnit.SECONDS) // Increase write timeout
                .connectTimeout(60, TimeUnit.SECONDS) // Increase connect timeout
                .build()

            Retrofit.Builder()
                .baseUrl(BlogCloudConfig.instance!!.baseUrl.toString())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        }

        val blogAPI: BlogAPI by lazy {
            blogCloud.create(BlogAPI::class.java)
        }
    }
}