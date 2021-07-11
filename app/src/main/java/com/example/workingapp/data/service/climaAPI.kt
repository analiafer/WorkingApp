package com.example.workingapp.data.service

import com.example.workingapp.model.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface climaAPI {

    //
    @GET("weather")

    fun getCurrentWeatherData(@Query("q") city: String,
                              @Query("appid") access_key: String,
                              @Query("lang") lang:String = "es",
                              @Query("units") units: String = "metric")
            : Call<WeatherModel>


}

