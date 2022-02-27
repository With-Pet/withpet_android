package com.withpet.withpet_android.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.ActivitySignUpBinding
import com.withpet.withpet_android.others.getLoginEnumFromInt

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        val pref = getSharedPreferences(resources.getString(R.string.login_type_key), MODE_PRIVATE)
        val value = pref.getInt(resources.getString(R.string.login_type), -1)
        Toast.makeText(this, "${getLoginEnumFromInt(value)}", Toast.LENGTH_SHORT).show()
        setButtonListener()
    }

    private fun setButtonListener() {
        binding.signUpStartButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}