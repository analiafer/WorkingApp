package com.example.workingapp.service

import com.example.workingapp.current
import com.example.workingapp.location
import com.google.gson.Gson
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    private  fun getAPI(): climaAPI{
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.weatherstack.com/current")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
        
        return retrofit.create(climaAPI::class.java)
    }

    fun getLocation(id: String, callback: Callback<location>) {
        getAPI().getLocation(id).enqueue(callback)
    }

    fun getCurrent(id: String, callback: Callback<current>){
        getAPI().getCurrent(id).enqueue(callback)
    }

}
