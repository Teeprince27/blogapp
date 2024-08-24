package com.temitope.blogwebsiteapplication.model

import com.google.gson.annotations.SerializedName



data class Post(


    @SerializedName("userId") val userId: String,
    val id: String,
    val title: String,
    val body: String,



)





