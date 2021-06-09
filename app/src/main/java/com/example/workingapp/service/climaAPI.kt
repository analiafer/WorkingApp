package com.example.workingapp.service

import com.example.workingapp.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface climaAPI {
    @GET("/current?")
    fun getCurrentWeatherData(@Query("access_key") access_key: String?, @Query("query") query: String?): Call<WeatherResponse?>?

}

