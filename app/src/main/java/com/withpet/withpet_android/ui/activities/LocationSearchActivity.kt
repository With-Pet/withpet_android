package com.withpet.withpet_android.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.ActivityLocationSearchBinding

class LocationSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_location_search)

        setToolbar()
    }

    private fun setToolbar() {
        binding.locationSearchToolbar.setNavigationOnClickListener { finish() }
    }
}