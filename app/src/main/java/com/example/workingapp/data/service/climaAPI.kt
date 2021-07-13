package com.example.workingapp.data.service

import com.example.workingapp.model.WeatherModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface climaAPI {

    @GET("forecast")
    suspend fun getCurrentWeatherData
                (@Query("q") city: String,
                 @Query("appid") access_key: String = "c0d4c0df755521b2be43fd8bf65f2791",
                 @Query("lang") lang:String = "es",
                 @Query("units") units: String = "metric")
            : Response<WeatherModel>
}

