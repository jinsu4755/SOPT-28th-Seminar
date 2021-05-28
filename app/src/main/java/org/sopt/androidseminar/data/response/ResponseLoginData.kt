package org.sopt.androidseminar.data.response

import com.google.gson.annotations.SerializedName

data class ResponseLoginData(
    val success: Boolean,
    val message: String,
    val data: Data?
) {
    data class Data(
        @SerializedName("UserId")
        val userId: Int,
        val user_nickname: String,
        val token: String
    )
}
