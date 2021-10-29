package com.example.workingapp.data

import com.example.workingapp.data.service.DataSource
import com.example.workingapp.model.WeatherModel
import retrofit2.Response

class ClimaImpl (private val data: DataSource) : RepositorioClima {

    override suspend fun getWeather(lat: String, lon : String): Response<WeatherModel> {
       return data.getWeather(lat, lon)
    }

}