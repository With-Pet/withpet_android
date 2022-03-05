package com.withpet.withpet_android.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView.ScaleType
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.PostViewPagerAdapter
import com.withpet.withpet_android.databinding.ActivityPostImageBinding

class PostImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_image)

        setViewPager()
        setBtnClickListener()
    }

    private fun setViewPager() {
        binding.postImageViewPager.adapter = PostViewPagerAdapter(ScaleType.FIT_CENTER) { }
        TabLayoutMediator(
            binding.postImageTabLayout,
            binding.postImageViewPager
        ) { _, _ -> }.attach()
    }

    private fun setBtnClickListener() {
        binding.postImageCloseButton.setOnClickListener { finish() }
    }

}