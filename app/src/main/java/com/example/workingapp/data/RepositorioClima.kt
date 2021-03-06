package com.example.workingapp.data

import com.example.workingapp.model.WeatherModel
import retrofit2.Response

interface  RepositorioClima{

    suspend fun getWeather(city: String) : Response<WeatherModel>

}