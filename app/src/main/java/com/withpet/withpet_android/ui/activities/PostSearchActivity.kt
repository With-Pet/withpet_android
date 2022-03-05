package com.withpet.withpet_android.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.PostListAdapter
import com.withpet.withpet_android.databinding.ActivityPostSearchBinding

class PostSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_search)

        setToolbar()
        setRecyclerView()
    }

    private fun setToolbar() {
        binding.postSearchBackButton.setOnClickListener { finish() }
    }

    private fun setRecyclerView() {
        binding.postSearchRecyclerView.adapter = PostListAdapter {

        }
        binding.postSearchRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}