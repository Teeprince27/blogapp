package com.temitope.blogwebsiteapplication.model

import com.google.gson.annotations.SerializedName

data class ComingPost(
    @SerializedName("posts") var posts: ArrayList<Post>
)
