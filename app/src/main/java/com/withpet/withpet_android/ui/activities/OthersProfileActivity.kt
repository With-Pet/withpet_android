package com.withpet.withpet_android.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.withpet.withpet_android.R
import com.withpet.withpet_android.adapter.CertificationAdapter
import com.withpet.withpet_android.adapter.RatingBarAdapter
import com.withpet.withpet_android.databinding.ActivityOthersProfileBinding

class OthersProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOthersProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_others_profile)

        setToolbar()
        setRecyclerView()
        setBtnClickListener()
    }

    private fun setToolbar() {
        binding.othersProfileToolbar.setNavigationOnClickListener { finish() }
        binding.othersProfileToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profileLikeMenu -> {
                    if (menuItem.isChecked) {
                        menuItem.isChecked = false
                        menuItem.setIcon(R.drawable.ic_like_border)
                    } else {
                        menuItem.isChecked = true
                        menuItem.setIcon(R.drawable.ic_like)
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun setRecyclerView() {
        binding.othersProfileReviewRecyclerView.adapter = RatingBarAdapter()
        binding.othersProfileReviewRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.certificationRecyclerView.adapter = CertificationAdapter()
        binding.certificationRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setBtnClickListener() {
        binding.othersProfileMoreReviewButton.setOnClickListener {
            startActivity(Intent(this, ReviewListActivity::class.java))
        }
    }
}