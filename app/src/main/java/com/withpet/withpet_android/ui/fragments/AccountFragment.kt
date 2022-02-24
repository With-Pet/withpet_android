package com.withpet.withpet_android.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kakao.sdk.user.UserApiClient
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.FragmentAccountBinding
import com.withpet.withpet_android.ui.activities.*

class AccountFragment : Fragment(R.layout.fragment_account) {

    companion object {
        private const val TAG = "ACCOUNT_FRAGMENT"
    }

    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_account,
            container,
            false
        )

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
        binding.petProfileMenu.setOnClickListener {
            startActivity(Intent(activity, MyPetListActivity::class.java))
        }
        binding.likePostMenu.setOnClickListener {
            startActivity(Intent(activity, LikePostListActivity::class.java))
        }
        binding.likePersonMenu.setOnClickListener {
            startActivity(Intent(activity, LikePersonListActivity::class.java))
        }
        binding.noticeMenu.setOnClickListener {
            startActivity(Intent(activity, OthersProfileActivity::class.java))
        }
        binding.faqMenu.setOnClickListener {
            startActivity(Intent(activity, OthersPetActivity::class.java))
        }
        binding.settingMenu.setOnClickListener {
            startActivity(Intent(activity, ReviewWriteActivity::class.java))
        }
        binding.logoutMenu.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.sing_out_message)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.ok) { _, _ ->
                UserApiClient.instance.logout { error ->
                    if (error != null) Log.e(TAG, "로그아웃 실패", error)
                    startActivity(Intent(activity, SignInActivity::class.java))
                    activity?.finish()
                }
            }.show()
    }
}