package com.withpet.withpet_android.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.ActivityReviewWriteBinding

class ReviewWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_review_write)

        setToolbar()
    }

    private fun setToolbar() {
        binding.reviewWriteToolbar.setNavigationOnClickListener { clickHomeMenu() }
        binding.reviewWriteToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profileSaveMenu -> {
                    clickSaveMenu()
                    true
                }
                else -> false
            }
        }
    }

    private fun clickHomeMenu() {
        // TODO: check changes and then confirm whether changes save
        finish()
    }

    private fun clickSaveMenu() {
        // TODO: check changes and then save changes
        finish()
    }
}