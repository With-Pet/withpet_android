package com.withpet.withpet_android.ui.fragments

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.FragmentAccountBinding
import com.withpet.withpet_android.enums.LoginEnum
import com.withpet.withpet_android.enums.getLoginEnumFromInt
import com.withpet.withpet_android.ui.activities.*

class AccountFragment : Fragment(R.layout.fragment_account) {

    companion object {
        private const val TAG = "ACCOUNT_FRAGMENT"
    }

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var gso: GoogleSignInOptions
    private lateinit var binding: FragmentAccountBinding
    private lateinit var pref: SharedPreferences

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

        pref = requireActivity().getSharedPreferences(
            getString(R.string.login_type_key),
            MODE_PRIVATE
        )

        initializeGoogleLogin()
        setMenuClickListener()
    }

    private fun initializeGoogleLogin() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
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
            showDialog(R.string.sign_out_message, ::signOut)
        }
        binding.withdrawalMenu.setOnClickListener {
            showDialog(R.string.unlink_message, ::unlink)
        }
    }

    private fun showDialog(message: Int, action: () -> Unit) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(message)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.ok) { _, _ ->
                action()
            }.show()
    }

    private fun signOut() {
        when (getLoginEnumFromInt(pref.getInt(getString(R.string.login_type), -1))) {
            LoginEnum.GOOGLE -> {
                googleSignInClient.signOut().addOnCompleteListener { task ->
                    if (task.isCanceled) Log.e(TAG, "구글 로그아웃 실패", task.exception)
                    moveToSignInActivity()
                }
            }
            LoginEnum.NAVER -> {
                NaverIdLoginSDK.logout()
                moveToSignInActivity()
            }
            LoginEnum.KAKAO -> {
                UserApiClient.instance.logout { error ->
                    if (error != null) Log.e(TAG, "카카오 로그아웃 실패", error)
                    moveToSignInActivity()
                }
            }
            else -> Log.e(TAG, "로그인 타입 없음")
        }
    }

    private fun unlink() {
        when (getLoginEnumFromInt(pref.getInt(getString(R.string.login_type), -1))) {
            LoginEnum.GOOGLE -> {
                googleSignInClient.revokeAccess()
                    .addOnCompleteListener { task ->
                        if (task.isCanceled) Log.e(TAG, "구글 연결 끊기 실패", task.exception)
                        moveToSignInActivity()
                    }
            }
            LoginEnum.NAVER -> {
                NidOAuthLogin().callDeleteTokenApi(requireContext(), object : OAuthLoginCallback {
                    override fun onSuccess() {
                        moveToSignInActivity()
                    }

                    override fun onFailure(httpStatus: Int, message: String) {
                        Log.e(TAG, "네이버 연결 끊기 실패: $message")
                        moveToSignInActivity()
                    }

                    override fun onError(errorCode: Int, message: String) {
                        onFailure(errorCode, message)
                    }
                })
            }
            LoginEnum.KAKAO -> {
                UserApiClient.instance.unlink { error ->
                    if (error != null) {
                        Log.e(TAG, "카카오 연결 끊기 실패", error)
                    } else {
                        moveToSignInActivity()
                    }
                }
            }
            else -> Log.e(TAG, "로그인 타입 없음")
        }
    }

    private fun moveToSignInActivity() {
        pref.edit().putInt(getString(R.string.login_type), -1).apply()
        startActivity(Intent(activity, SignInActivity::class.java))
        activity?.finish()
    }
}