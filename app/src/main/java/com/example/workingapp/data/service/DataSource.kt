package com.example.workingapp.data.service

import com.example.workingapp.model.WeatherModel
import retrofit2.Response

class DataSource {

    suspend fun getWeather(ciudad: String): Response<WeatherModel> {
        return API.service.getCurrentWeatherData(ciudad)
    }

}