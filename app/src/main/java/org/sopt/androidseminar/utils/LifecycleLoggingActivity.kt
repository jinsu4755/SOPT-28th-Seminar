package org.sopt.androidseminar.utils

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

abstract class LifecycleLoggingActivity : AppCompatActivity() {

    private val viewName by lazy { this.localClassName.split("Activity")[0] }

    private fun logLifecycle(message: String) {
        Log.d("[$viewName] Lifecycle", message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("---------------------------------------------")
        logLifecycle("onCreate()")
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycle("onRestart()")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume()")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("onPause()")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("onDestroy()")
    }
}
