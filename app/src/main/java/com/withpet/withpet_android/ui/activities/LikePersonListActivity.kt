package com.withpet.withpet_android.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.LikePersonListAdapter
import com.withpet.withpet_android.databinding.ActivityLikePersonListBinding

class LikePersonListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLikePersonListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_like_person_list)

        setToolbar()
        setRecyclerView()
    }

    private fun setToolbar() {
        binding.likePersonListToolbar.setNavigationOnClickListener { finish() }
    }

    private fun setRecyclerView() {
        binding.likePersonListRecyclerView.adapter = LikePersonListAdapter {
            startActivity(Intent(this, OthersProfileActivity::class.java))
        }
        binding.likePersonListRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}