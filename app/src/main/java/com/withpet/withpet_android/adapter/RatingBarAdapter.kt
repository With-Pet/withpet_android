package com.withpet.withpet_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.AdapterViewRatingBarBinding

class RatingBarAdapter : RecyclerView.Adapter<RatingBarAdapter.RatingBarViewHolder>() {
    val ratingNumArr = intArrayOf(4, 0, 33, 18, 45)

    inner class RatingBarViewHolder(
        private val binding: AdapterViewRatingBarBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, count: Int, sum: Int) {
            binding.ratingScoreTextView.text = "$position" + "점"
            binding.ratingCountTextView.text = "$count"
            binding.ratingProgressBar.progress = (count * 100) / sum
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingBarViewHolder {
        val binding = DataBindingUtil.inflate<AdapterViewRatingBarBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_view_rating_bar, parent, false
        )
        return RatingBarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RatingBarViewHolder, position: Int) {
        holder.bind(5 - position, ratingNumArr[4 - position], 100)
    }

    override fun getItemCount() = 5
}