package com.withpet.withpet_android.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.FragmentAccountBinding
import com.withpet.withpet_android.ui.activities.MyProfileActivity

class AccountFragment : Fragment(R.layout.fragment_account) {

    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMenuClickListener()
    }

    private fun setMenuClickListener() {
        binding.myProfileMenu.setOnClickListener {
            startActivity(Intent(activity, MyProfileActivity::class.java))
        }
    }
}