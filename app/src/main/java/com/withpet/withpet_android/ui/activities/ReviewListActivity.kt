package com.withpet.withpet_android.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.ReviewAdapter
import com.withpet.withpet_android.databinding.ActivityReviewListBinding

class ReviewListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_review_list)

        setToolbar()
        setRecyclerView()
    }

    private fun setToolbar() {
        binding.reviewListToolbar.setNavigationOnClickListener { finish() }
    }

    private fun setRecyclerView() {
        binding.reviewListRecyclerView.adapter = ReviewAdapter()
        binding.reviewListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.reviewListRecyclerView.addItemDecoration(DividerItemDecoration(this, VERTICAL))
    }
}