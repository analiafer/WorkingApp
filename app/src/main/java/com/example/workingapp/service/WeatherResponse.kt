package com.example.workingapp

import com.google.gson.annotations.SerializedName

  data class WeatherResponse(
      @SerializedName("location")
      var location: Location,
      @SerializedName("current")
    var current: Current,
      @SerializedName("type")
    var type: String,
      @SerializedName("query")
    var ciudad: String,
      @SerializedName("language")
    var language: String,
      @SerializedName("unit")
    var unidad: String)



data class Location(
    @SerializedName("name")
    var name: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("region")
    var region: String,
    @SerializedName("lat")
    var lat: String,
    @SerializedName("lon")
    var lon: String,
    @SerializedName("timezone_id")
    var timezone: String,
    @SerializedName("localtime")
    var localtime: String,
    @SerializedName("localtime_epoch")
    var localtimeEpoch: Long
)

data class Current(
    @SerializedName("observation_time")
    var observationTime: String,
    @SerializedName("temperature")
    var temperature: Long,
    @SerializedName("weather_code")
    var weatherCode: Long,
    @SerializedName("weather_descriptions")
    var weather: ArrayList<String>,
    @SerializedName("wind_speed")
    var windSpeed: Long,
    @SerializedName("wind_degree")
    var windDegree: Long,
    @SerializedName("wind_dir")
    var windDir: String,
    @SerializedName("pressure")
    var pressure: Long,
    @SerializedName("humidity")
    var humidity: Long,
    @SerializedName("cloudcover")
    var cloudCover: Long,
    @SerializedName("feelslike")
    var feelsLike: Long,
    @SerializedName("uv_index")
    var uvIndex: Long,
    @SerializedName("visibility")
    var visibility: Long
)
