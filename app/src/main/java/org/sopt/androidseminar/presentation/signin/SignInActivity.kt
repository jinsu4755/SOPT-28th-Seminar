package org.sopt.androidseminar.presentation.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.androidseminar.api.ServiceCreator
import org.sopt.androidseminar.data.SoptUserAuthStorage
import org.sopt.androidseminar.data.local.SoptUserInfo
import org.sopt.androidseminar.data.request.RequestLoginData
import org.sopt.androidseminar.data.response.ResponseLoginData
import org.sopt.androidseminar.databinding.ActivitySignInBinding
import org.sopt.androidseminar.presentation.home.HomeActivity
import org.sopt.androidseminar.presentation.signup.SignUpActivity
import org.sopt.androidseminar.utils.LifecycleLoggingActivity
import org.sopt.androidseminar.utils.enqueueUtil
import org.sopt.androidseminar.utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : LifecycleLoggingActivity() {

    private lateinit var binding: ActivitySignInBinding

    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        binding.loginIdInput.setText(it.data?.getStringExtra("id"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // UserAuthStorage 를 찾아본다
        searchUserAuthStorage()
        setButtonEvent()
    }

    private fun searchUserAuthStorage() {
        with(SoptUserAuthStorage.getInstance(this)) {
            if (hasUserData()) {
                requestLogin(getUserData().let { RequestLoginData(it.id, it.password) })
            }
        }
    }

    private fun setButtonEvent() {
        with(binding) {
            loginButton.setOnClickListener { loginButtonClickEvent() }
            goSignUpLayout.setOnClickListener { startSignUpForResult() }
        }
    }

    private fun loginButtonClickEvent() {
        // 서버로 보낼 로그인 데이터 생성
        val requestLoginData = RequestLoginData(
            id = binding.loginIdInput.text.toString(),
            password = binding.loginPasswordInput.text.toString()
        )
        // 서버로 보낼 로그인 데이터로 서버 통신 요청
        // 4차 세미나에 만든 메소드 재사용
        // -> 매개변수로 data class 를 넣어주기만 하면 해당 데이터로 로그인
        requestLogin(requestLoginData)
    }

    private fun requestLogin(requestLoginData: RequestLoginData) {
        // 현재 사용자의 정보를 받아올 것을 명시!
        // 서버 통신은 I/O 작업이므로 비동기적으로 받아올 Callback 내부 코드는 나중에
        // 데이터를 받아오고 실행된다.
        val call: Call<ResponseLoginData> = ServiceCreator.soptService
            .postLogin(requestLoginData)
        call.enqueueUtil(
            onSuccess = { response ->
                val data = response.data
                showToast(data?.user_nickname.toString())
                with(SoptUserAuthStorage.getInstance(this)) {
                    saveUserData(requestLoginData.let { SoptUserInfo(it.id, it.password) })
                }
                startHomeActivity()
            }
        )
    }

    private fun startHomeActivity() {
        startActivity(
            Intent(this, HomeActivity::class.java)
        )
        finish()
    }

    private fun startSignUpForResult() {
        signUpActivityLauncher.launch(
            Intent(this, SignUpActivity::class.java)
        )
    }
}
