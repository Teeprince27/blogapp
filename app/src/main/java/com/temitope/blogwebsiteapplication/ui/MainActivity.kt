package com.temitope.blogwebsiteapplication.ui

import AppRepository
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.temitope.blogwebsiteapplication.adapter.BlogPostAdapter
import com.temitope.blogwebsite.util.Status
import com.temitope.blogwebsiteapplication.databinding.ActivityMainBinding
import com.temitope.blogwebsiteapplication.viewmodel.BlogViewModel
import com.temitope.blogwebsiteapplication.viewmodel.ViewModelProviderFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var blogViewModel: BlogViewModel

    private var blogPostAdapter: BlogPostAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        initializeViews()

        runBlocking {
            getPost()
        }

    }

    private fun init() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        blogViewModel = ViewModelProvider(this, factory)[BlogViewModel::class.java]
    }

    private fun initializeViews() {
        binding.rvPosts.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        binding.rvPosts.setHasFixedSize(true)
    }

    private fun getPost() {
        blogViewModel.viewModelScope.launch {
            blogViewModel.getPost().collect {
                when (it.status) {
                    Status.Loading -> {

                    }

                    Status.Success -> {
                        blogPostAdapter = BlogPostAdapter(it.data!!.posts)
                        binding.rvPosts.adapter = blogPostAdapter

                    }

                    Status.Error -> {

                    }
                }
            }
        }

    }
}