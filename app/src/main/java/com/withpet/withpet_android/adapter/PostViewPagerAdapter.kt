package com.withpet.withpet_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView.ScaleType
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.AdapterViewPostImageBinding

class PostViewPagerAdapter(
    private val scaleType: ScaleType,
    private val itemClickListener: () -> Unit
) : RecyclerView.Adapter<PostViewPagerAdapter.PostImageViewHolder>() {
    inner class PostImageViewHolder(
        private val binding: AdapterViewPostImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setScaleType(type: ScaleType) {
            binding.postImageView.scaleType = type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostImageViewHolder {
        val binding = DataBindingUtil.inflate<AdapterViewPostImageBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_view_post_image,
            parent,
            false
        )
        return PostImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostImageViewHolder, position: Int) {
        holder.setScaleType(scaleType)
        holder.itemView.setOnClickListener { itemClickListener() }
    }

    override fun getItemCount() = 5
}