package com.example.workingapp.service

import com.example.workingapp.model.WeatherModel
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    private  fun getAPI(): climaAPI{

        val baseUrl = "https://api.weatherstack.com/"
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(climaAPI::class.java)
    }

    fun getCurrentWeatherData(accessKey: String, ciudad: String, callback: Callback<WeatherModel>) {
        getAPI().getCurrentWeatherData(accessKey, ciudad).enqueue(callback)
    }


}