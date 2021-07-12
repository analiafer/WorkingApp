package com.example.workingapp.data.service

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {

    val baseUrl = "https://api.openweathermap.org/data/2.5/"

    val service by lazy{
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(climaAPI::class.java)
    }

}