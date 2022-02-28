package com.withpet.withpet_android.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.PostListAdapter
import com.withpet.withpet_android.databinding.ActivityLikePostListBinding

class LikePostListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLikePostListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_like_post_list)

        setToolbar()
        setRecyclerView()
    }

    private fun setToolbar() {
        binding.likePostListToolbar.setNavigationOnClickListener { finish() }
    }

    private fun setRecyclerView() {
        binding.likePostListRecyclerView.adapter = PostListAdapter {

        }
        binding.likePostListRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}