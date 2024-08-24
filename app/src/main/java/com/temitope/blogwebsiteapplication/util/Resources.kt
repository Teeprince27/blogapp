package com.temitope.blogwebsite.util


data class Resources<T>(
    val status: Status,
    val message: String? = null,
    val data: T? = null
) {
    companion object {
        fun <T> loading(): Resources<T> = Resources(Status.Loading, null, null)
        fun <T> error(message: String): Resources<T> = Resources(Status.Error, message, null)
        fun <T> success(message: String, data: T?): Resources<T> =
            Resources(Status.Success, message, data)
    }
}