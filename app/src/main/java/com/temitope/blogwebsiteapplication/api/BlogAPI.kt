package com.temitope.blogwebsiteapplication.api


import com.temitope.blogwebsiteapplication.model.ComingPost
import com.temitope.blogwebsiteapplication.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface BlogAPI {

    @GET("posts")
    @Headers(
//        "X-RapidAPI-Key: ${Constants.X_RAPID_API_KEY}",
//        "X-RapidAPI-Host: ${Constants.X_RAPID_API_HOST}"
    )
    suspend fun getPost(): Response<Post>
}