package com.withpet.withpet_android.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setBottomNavigationView()
    }

    private fun setBottomNavigationView() {
        val navHost = supportFragmentManager.findFragmentById(R.id.mainNavHost) as NavHostFragment
        binding.mainBottomNavigationView.setupWithNavController(navHost.navController)
        binding.mainBottomNavigationView.background = null
        binding.mainBottomNavigationView.menu.getItem(2).isEnabled = false

        binding.mainFab.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}