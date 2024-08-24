package com.temitope.blogwebsiteapplication.viewmodel


import AppRepository
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.temitope.blogwebsiteapplication.model.ComingPost
import com.temitope.blogwebsiteapplication.model.ErrorMessage
import com.temitope.blogwebsiteapplication.model.Post
import com.temitope.blogwebsite.util.Constants
import com.temitope.blogwebsite.util.Resources
import com.temitope.blogwebsite.util.Utils
import com.temitope.blogwebsiteapplication.MyApplication
import com.temitope.blogwebsiteapplication.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class BlogViewModel(
    app: Application,
    private val repository: AppRepository
) : AndroidViewModel(app) {

    private inline fun <reified T> handleApiCall(
        crossinline apiCall: suspend () -> Response<T>
    ): Flow<Resources<T>> = flow {
        emit(Resources.loading())
        try {
            if (Utils.hasInternetConnection(getApplication())) {
                val response = apiCall.invoke()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    if (data is Post
                    ) {
                        emit(Resources.success("success", data))
                        Log.d("RESPONSE:{}{}", "SUCCESS: ${data}")
                    } else {
                        emit(Resources.error("Unknown error occurred"))
                    }
                } else {
                    val gson = Gson()
                    val message: ErrorMessage = gson.fromJson(
                        response.errorBody()?.charStream(),
                        ErrorMessage::class.java
                    )
                    if (message.message == null) {
                        emit(Resources.error("The service is down, contact support"))
                    }
                    emit(Resources.error(message.message.toString()))
                    Log.d("_&check_device", "error: ${response.raw().networkResponse}")
                }
            } else emit(Resources.error(Constants.NO_INTERNET))
        } catch (e: Exception) {
            emit(
                Resources.error(
                    getApplication<MyApplication>()
                        .getString(R.string.network_failure)
                )
            )
            Log.d("_&check_device", "error: $e")
        }
    }

    fun getPost(
    ): Flow<Resources<ComingPost>> = handleApiCall {
        repository.getPost()
    }
}