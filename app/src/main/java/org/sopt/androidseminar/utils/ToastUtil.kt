package org.sopt.androidseminar.utils

import android.content.Context
import android.content.res.AssetManager
import android.widget.Toast

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        .show()
}
