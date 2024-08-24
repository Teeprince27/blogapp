package com.temitope.blogwebsiteapplication.api


class BlogCloudConfig private constructor() {
    val baseUrl: String?
        get() = Companion.baseUrl

    companion object {
        @JvmStatic
        var instance: BlogCloudConfig? = null
            get() {
                if (field == null) {
                    synchronized(BlogCloud::class.java) {
                        if (field == null)
                            field = BlogCloudConfig()
                    }
                }
                return field
            }
            private set
        private var baseUrl: String? = null
    }

    init {
        Companion.baseUrl = "https://jsonplaceholder.typicode.com/"
    }

}