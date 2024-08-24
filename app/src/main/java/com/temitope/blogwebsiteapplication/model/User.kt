package com.temitope.blogwebsiteapplication.model

import com.temitope.blogwebsiteapplication.model.Address
import com.temitope.blogwebsiteapplication.model.Company

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)
