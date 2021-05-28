package org.sopt.androidseminar.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// kotlin 에서 object 는 singleton 을 구현하는 신기한 친구!
object ServiceCreator {
    private const val BASE_URL = "http://cherishserver.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val soptService: SoptService = retrofit.create(SoptService::class.java)
}
