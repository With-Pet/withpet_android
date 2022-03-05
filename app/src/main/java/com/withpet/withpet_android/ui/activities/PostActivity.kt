package com.withpet.withpet_android.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.PostViewPagerAdapter
import com.withpet.withpet_android.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)

        setToolbar()
        setViewPager()
        setBtnClickListener()
    }

    private fun setToolbar() {
        binding.postToolbar.setNavigationOnClickListener { finish() }
    }

    private fun setViewPager() {
        binding.postViewPager.adapter = PostViewPagerAdapter(ImageView.ScaleType.CENTER_CROP) {
            startActivity(Intent(this, PostImageActivity::class.java))
        }
        TabLayoutMediator(binding.postTabLayout, binding.postViewPager) { _, _ -> }.attach()
    }

    private fun setBtnClickListener() {
        binding.postMasterProfileImage.setOnClickListener {
            startActivity(Intent(this, OthersProfileActivity::class.java))
        }
    }
}