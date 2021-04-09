package org.sopt.androidseminar.presentation.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.androidseminar.databinding.ActivitySignInBinding
import org.sopt.androidseminar.presentation.home.HomeActivity
import org.sopt.androidseminar.presentation.signup.SignUpActivity
import org.sopt.androidseminar.utils.LifecycleLoggingActivity

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
        initButtonEvent()
    }

    private fun initButtonEvent() {
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.goSignUpLayout.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }

    companion object {
        const val SIGN_UP_RESULT_CODE = 101
    }
}
