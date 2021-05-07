package org.sopt.androidseminar.presentation.home

import android.os.Bundle
import org.sopt.androidseminar.R
import org.sopt.androidseminar.utils.LifecycleLoggingActivity

class HomeActivity : LifecycleLoggingActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
