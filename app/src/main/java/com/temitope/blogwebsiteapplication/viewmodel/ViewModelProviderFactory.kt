package com.temitope.blogwebsiteapplication.viewmodel

import AppRepository
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelProviderFactory(
    private val app: Application,
    private val appRepository: AppRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(BlogViewModel::class.java)) {
            return BlogViewModel(app, appRepository) as T
        }


        throw IllegalArgumentException("Unknown class name")
    }

}
