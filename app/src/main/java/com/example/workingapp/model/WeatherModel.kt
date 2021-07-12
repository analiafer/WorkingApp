package com.example.workingapp.model


import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("list")
    val list: List<WeatherList>,
    @SerializedName("message")
    val message: Int
)