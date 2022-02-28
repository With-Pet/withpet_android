package com.withpet.withpet_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.AdapterViewLikePostListBinding

class PostListAdapter(
    private val itemClickListener: () -> Unit
) : RecyclerView.Adapter<PostListAdapter.PostListViewHolder>() {

    inner class PostListViewHolder(
        binding: AdapterViewLikePostListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val binding = DataBindingUtil.inflate<AdapterViewLikePostListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_view_like_post_list,
            parent,
            false
        )

        binding.root.setOnClickListener {
            itemClickListener()
        }

        return PostListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        // TODO("Not yet implemented")
    }

    override fun getItemCount() = 7
}