package com.withpet.withpet_android.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.ActivitySignInBinding
import com.withpet.withpet_android.others.LoginEnum


class SignInActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SIGN_IN_ACTIVITY"
    }

    private lateinit var pref: SharedPreferences
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivitySignInBinding
    private lateinit var gso: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private val activityResultCallback = ActivityResultCallback<ActivityResult> { result ->
        if (result.resultCode == RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleGoogleSignInResult(task)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pref = getSharedPreferences(getString(R.string.login_type_key), MODE_PRIVATE)
        launcher = registerForActivityResult(StartActivityForResult(), activityResultCallback)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        initializeGoogleLogin()
        setBtnClickListener()
    }

    override fun onStart() {
        super.onStart()
        // TODO: Already sign-in, Move to Splash Activity later
        val account = GoogleSignIn.getLastSignedInAccount(this)
        // if (account != null) checkAccount(LoginEnum.GOOGLE)
    }

    private fun initializeGoogleLogin() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.googleSignInButton.setColorScheme(SignInButton.COLOR_DARK)
        binding.googleSignInButton.setSize(SignInButton.SIZE_WIDE)
        (binding.googleSignInButton[0] as TextView).text = getString(R.string.google_sign_in)
    }

    private fun setBtnClickListener() {
        binding.googleSignInButton.setOnClickListener { googleSignIn() }
        binding.naverSignInButton.setOnClickListener { checkNaverToken() }
        binding.kakaoSignInButton.setOnClickListener { checkKakaoToken() }
    }

    private fun googleSignIn() {
        launcher.launch(googleSignInClient.signInIntent)
    }

    private fun handleGoogleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
            checkAccount(LoginEnum.GOOGLE)
        } catch (error: ApiException) {
            Log.e(TAG, "구글 로그인 실패", error)
        }
    }

    private fun checkNaverToken() {
        // TODO: Move to Splash Activity later
        NaverIdLoginSDK.authenticate(this, object : OAuthLoginCallback {
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }

            override fun onFailure(httpStatus: Int, message: String) {
                Log.e(TAG, "네이버 로그인 실패: $message")
            }

            override fun onSuccess() {
                checkAccount(LoginEnum.NAVER)
            }
        })
    }

    private fun checkKakaoToken() {
        // TODO: Move to Splash Activity later
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
                        kakaoSignIn()
                    } else {
                        Log.e(TAG, "기타 에러", error)
                    }
                } else {
                    checkAccount(LoginEnum.KAKAO)
                }
            }
        } else {
            kakaoSignIn()
        }
    }

    private fun kakaoSignIn() {
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
                checkAccount(LoginEnum.KAKAO)
            }
        }
    }

    private fun signInWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오 계정 로그인 실패", error)
            } else if (token != null) {
                checkAccount(LoginEnum.KAKAO)
            }
        }
    }

    private fun checkAccount(type: LoginEnum) {
        // TODO: When our server's api is ready, it will be implemented.
        saveLoginType(type)
        startActivity(Intent(this, SignUpActivity::class.java))
        finish()
    }

    private fun saveLoginType(type: LoginEnum) {
        pref.edit()
            .putInt(getString(R.string.login_type), type.value)
            .apply()
    }
}