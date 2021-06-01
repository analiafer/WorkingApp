package com.example.workingapp.service

import com.example.workingapp.current
import com.example.workingapp.location
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface climaAPI{
        @GET("items/{itemId}")
        fun getLocation(@Path("itemId") id: String): Call<location>

        @GET("items/{itemId}/current")
        fun getCurrent(@Path("itemId") id: String): Call<current>
}