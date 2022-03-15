package com.withpet.withpet_android.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.ActivityReservationDetailBinding

class ReservationDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservationDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reservation_detail)

        setToolbar()
        setBtnClickListener()
    }

    private fun setToolbar() {
        binding.reservationDetailToolbar.setNavigationOnClickListener { finish() }
    }

    private fun setBtnClickListener() {
        binding.reservationDetailReviewButton.setOnClickListener {
            startActivity(Intent(this, ReviewWriteActivity::class.java))
        }
    }
}