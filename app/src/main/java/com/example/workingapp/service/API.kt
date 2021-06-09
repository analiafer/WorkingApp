package com.example.workingapp.service

import com.example.workingapp.WeatherResponse
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    private  fun getAPI(): climaAPI{

        val baseUrl = "http://api.weatherstack.com"
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(climaAPI::class.java)
    }

    fun getCurrentWeatherData(access_key: String, query: String, callback: Callback<WeatherResponse?>){
        getAPI().getCurrentWeatherData(access_key, query)?.enqueue(callback)
    }


}
