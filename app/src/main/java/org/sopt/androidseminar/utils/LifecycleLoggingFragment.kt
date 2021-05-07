package org.sopt.androidseminar.utils

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class LifecycleLoggingFragment : Fragment() {

    private val viewName by lazy { javaClass.name.split(".").last() }

    private fun logLifecycle(message: String) {
        Log.d("[$viewName] Lifecycle", message)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logLifecycle("*********")
        logLifecycle("onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logLifecycle("onCreateView()")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logLifecycle("onViewCreated()")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        logLifecycle("onViewStateRestored()")
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("onStart()")
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logLifecycle("onSaveInstanceState()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logLifecycle("onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        logLifecycle("onDetach()")
    }
}
