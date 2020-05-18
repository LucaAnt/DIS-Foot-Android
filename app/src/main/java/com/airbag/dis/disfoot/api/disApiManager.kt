package com.airbag.dis.disfoot.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class disApiManager {
val dis by lazy {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.designitalianshoes.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        retrofit.create(IDisApi::class.java)
}
}
