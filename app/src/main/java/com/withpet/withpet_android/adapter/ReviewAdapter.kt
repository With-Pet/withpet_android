package com.withpet.withpet_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.AdapterViewReviewBinding

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    inner class ReviewViewHolder(
        binding: AdapterViewReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = DataBindingUtil.inflate<AdapterViewReviewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_view_review,
            parent,
            false
        )
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        // TODO("Not yet implemented")
    }

    override fun getItemCount() = 5
}