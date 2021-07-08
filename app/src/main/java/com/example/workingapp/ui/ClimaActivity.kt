package com.example.workingapp.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workingapp.databinding.ActivityClimaBinding
import com.example.workingapp.model.WeatherModel
import com.example.workingapp.data.service.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class ClimaActivity : AppCompatActivity() {

    private lateinit var bindingClima: ActivityClimaBinding

   private var accessKey: String ="c0d4c0df755521b2be43fd8bf65f2791"
   private var ciudad: String = "Buenos Aires"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClima = ActivityClimaBinding.inflate(layoutInflater)
        setContentView(bindingClima.root)
        val appbarnav = bindingClima.tbClima
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getCurrentData()
    }

    private fun getCurrentData() {
        API().getCurrentWeatherData(ciudad, accessKey, object : Callback<WeatherModel> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.isSuccessful){
                    Log.i("ClimaActivity", "Comienza el metodo getCurrentData")
                    response.body()!!.apply {
                        bindingClima.txtCity.text = this.name + ", " + this.sys.country
                        bindingClima.txtTemperatura.text = this.main.temp.toLong().toString()
                        bindingClima.txtTemperaturaMax.text = this.main.tempMax.toLong().toString() + "°"
                        bindingClima.txtTemperaturaMin.text = this.main.tempMin.toLong().toString() + "°"
                        bindingClima.txtEstado.text = this.weather[0].description
                        bindingClima.txtViento.text = this.wind.speed.toString() + " km"
                        bindingClima.txtHumedad.text =this.main.humidity.toString() + "%"
                        bindingClima.txtPresion.text = this.main.pressure.toString()
                        bindingClima.txtActCiudad.text =  SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(this.dt.toLong()*1000))
                        bindingClima.txtAmanecer.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(this.sys.sunset.toLong()*1000))
                        bindingClima.txtAtardecer.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(this.sys.sunrise.toLong()*1000))
                        bindingClima.txtSTermica.text = this.main.feelsLike.toLong().toString() + "°"
                        Log.i("ClimaActivity", "Respuesta del getCurrentData")
                    }
                }else{
                    Toast.makeText(this@ClimaActivity, "Fallo con codigo ${response.code()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.e("MainActivity", "Fallo al obtener datos", t)
            }
        })
    }
}
