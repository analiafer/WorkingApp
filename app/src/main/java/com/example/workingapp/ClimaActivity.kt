package com.example.workingapp


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workingapp.databinding.ActivityClimaBinding
import com.example.workingapp.model.WeatherModel
import com.example.workingapp.service.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ClimaActivity : AppCompatActivity() {

    private lateinit var bindingClima: ActivityClimaBinding

   private var accessKey: String ="7a9237f486a2764a3e71ad428f1987f0"
   private var ciudad: String = "Londres"

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
        API().getCurrentWeatherData(accessKey, ciudad, object : Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.isSuccessful){
                    response.body()!!.apply {
                        bindingClima.txtCity.text = location.name
                        bindingClima.txtTemperatura.text = current.temperature.toString()
                        bindingClima.txtEstado.text = current.weatherDescriptions[0]
                        bindingClima.txtViento.text = current.windSpeed.toString()
                        bindingClima.txtHumedad.text = current.humidity.toString()
                        bindingClima.txtPresion.text = current.pressure.toString()
                        bindingClima.txtLluvia.text = current.precip.toString()
                        bindingClima.txtActCiudad.text = location.localtime
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
