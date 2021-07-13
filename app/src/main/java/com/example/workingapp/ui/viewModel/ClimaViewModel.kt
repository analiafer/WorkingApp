package com.example.workingapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.workingapp.data.RepositorioClima
import com.example.workingapp.model.WeatherModel
import retrofit2.Response

class ClimaViewModel(private val repo: RepositorioClima): ViewModel(){

    val clima = liveData<Response<WeatherModel>> {
        val response = repo.getWeather("Buenos Aires")
        if(response.isSuccessful){
            response.body()
            emit(response)
        }
    }
}
