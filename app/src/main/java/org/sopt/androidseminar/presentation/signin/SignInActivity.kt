package org.sopt.androidseminar.presentation.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.androidseminar.api.ServiceCreator
import org.sopt.androidseminar.data.SoptUserAuthStorage
import org.sopt.androidseminar.data.request.RequestLoginData
import org.sopt.androidseminar.data.response.ResponseLoginData
import org.sopt.androidseminar.databinding.ActivitySignInBinding
import org.sopt.androidseminar.presentation.home.HomeActivity
import org.sopt.androidseminar.presentation.signup.SignUpActivity
import org.sopt.androidseminar.utils.LifecycleLoggingActivity
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
        // UserAuthStorage 에 데이터가 있다면
        if (hasUserAuthData()) {
            // 저장된 id/pw로 로그인을 한다
            requestLogin(
                RequestLoginData(
                    id = SoptUserAuthStorage.getUserId(this),
                    password = SoptUserAuthStorage.getUserPw(this)
                )
            )
        }
    }

    // UserAuthStorage 에서 id, pw가 있는지 찾는다.
    private fun hasUserAuthData() = SoptUserAuthStorage.getUserId(this).isNotEmpty() &&
        SoptUserAuthStorage.getUserPw(this).isNotEmpty()

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
        /* enqueue 함수를 이용해 Call 이 비동기 작업이후 동작할 Callback 을 등록할 수 있다.
        * 해당 함수 호출은 Callback 을 등록만하고 
        * 실제 서버 통신을 요청이후 통신 결과가 나왔을때 실행된다.*/
        // object 키워드로 Callback 을 구현할 익명 클래스를 생성
        call.enqueue(object : Callback<ResponseLoginData> {
            // 네트워크 통신 Response 가 있는 경우 해당 함수를 retrofit 이 호출
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>
            ) {
                // 네트워크 통신에 성공한 경우 status 코드가 200~300일때! 실행
                if (response.isSuccessful) {
                    // response body 자체가 nullable 데이터! 그런데 서버에서 오는 data 도 nullable!
                    val data = response.body()?.data
                    // 통신 성공시 유저 닉네임을 보여준다.
                    Toast.makeText(this@SignInActivity, data?.user_nickname, Toast.LENGTH_SHORT)
                        .show()
                    with(binding) {
                        SoptUserAuthStorage.saveUserId(this@SignInActivity, loginIdInput.text.toString())
                        SoptUserAuthStorage.saveUserPw(this@SignInActivity, loginPasswordInput.text.toString())
                    }
                    // 홈 화면으로 넘어간다.
                    startHomeActivity()
                }
            }

            // 네트워크 통신 자체가 실패한 경우 해당 함수를 retrofit 이 실행!
            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                Log.d("NetworkTest", "error:$t")
            }
        })
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
