package com.withpet.withpet_android.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SIGN_IN_ACTIVITY"
    }

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        binding.signInButton.setOnClickListener { checkToken() }
    }

    private fun checkToken() {
        // TODO: Move to Splash Activity later
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
                        signIn()
                    } else {
                        Log.e(TAG, "기타 에러", error)
                    }
                } else {
                    checkAccount()
                }
            }
        } else {
            signIn()
        }
    }

    private fun signIn() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            signInWithKakaoTalk()
        } else {
            signInWithKakaoAccount()
        }
    }

    private fun signInWithKakaoTalk() {
        UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오톡 로그인 실패", error)

                // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    // TODO: Add a SnackBar for login failure message
                    return@loginWithKakaoTalk
                } else {
                    signInWithKakaoAccount()
                }
            } else if (token != null) {
                checkAccount()
            }
        }
    }

    private fun signInWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오 계정 로그인 실패", error)
            } else if (token != null) {
                checkAccount()
            }
        }
    }

    private fun checkAccount() {
        // TODO: When our server's api is ready, it will be implemented.
        startActivity(Intent(this, SignUpActivity::class.java))
        finish()
    }
}