package com.withpet.withpet_android.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        val email = intent?.extras?.getString("email")
        val bundle = Bundle().apply { putString("email", email) }
        val navHost = supportFragmentManager.findFragmentById(R.id.signUpNavHost) as NavHostFragment
        navHost.navController.navigate(R.id.globalActionToEmailFragment, bundle)
    }
}