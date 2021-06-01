package com.example.workingapp

import com.google.gson.annotations.SerializedName

data class climaApi(
    var location: List<location>,
    var current: List<current>
)

data class location(
    @SerializedName("name")
    var name: String,
    @SerializedName("country")
    var country: String
)

data class current(
    @SerializedName("temperature")
    var temp: Long,
    var weather_descriptions: List<weatherDesc>,
    @SerializedName("wind_speed")
    var windSpeed: String,
    @SerializedName("pressure")
    var pressure: String,
    @SerializedName("humidity")
    var humidity: String,
    @SerializedName("precip")
    var precip: String
)

data class weatherDesc(
    @SerializedName("weather_descriptions")
    var description: String
)