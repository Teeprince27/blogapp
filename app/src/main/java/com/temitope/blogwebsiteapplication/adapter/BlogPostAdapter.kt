package com.temitope.blogwebsiteapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.temitope.blogwebsiteapplication.databinding.ItemBlogPostBinding
import com.temitope.blogwebsiteapplication.model.Post

class BlogPostAdapter (
    private var post: ArrayList<Post>
): RecyclerView.Adapter<BlogPostAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemBlogPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(post[position])
    }

    override fun getItemCount(): Int =
        post.size

    inner class MyHolder(private var binding: ItemBlogPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.tvId.text = post.id
            binding.tvTitle.text = post.title
            binding.tvBlogBody.text = post.body
        }
    }
}
