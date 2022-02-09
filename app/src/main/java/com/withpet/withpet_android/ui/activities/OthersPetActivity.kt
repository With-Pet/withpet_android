package com.withpet.withpet_android.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.RatingBarAdapter
import com.withpet.withpet_android.databinding.ActivityOthersPetBinding

class OthersPetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOthersPetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_others_pet)

        setToolbar()
        setRecyclerView()
    }

    private fun setToolbar() {
        binding.othersPetToolbar.setNavigationOnClickListener { finish() }
    }

    private fun setRecyclerView() {
        binding.othersPetReviewRecyclerView.adapter = RatingBarAdapter()
        binding.othersPetReviewRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}