package org.sopt.androidseminar.presentation.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import org.sopt.androidseminar.databinding.ActivitySignUpBinding
import org.sopt.androidseminar.utils.LifecycleLoggingActivity

class SignUpActivity : LifecycleLoggingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra("id", binding.signUpNameInput.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
