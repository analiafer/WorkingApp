package com.example.workingapp.ui


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.example.workingapp.R
import com.example.workingapp.data.SharedPref
import com.example.workingapp.databinding.ActivityClimaBinding
import com.example.workingapp.model.WeatherList
import com.example.workingapp.ui.viewModel.ClimaViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class ClimaActivity : AppCompatActivity() {

    private lateinit var bindingClima: ActivityClimaBinding
    private val viewModelClima: ClimaViewModel by viewModel()
    private lateinit var clima: WeatherList

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    internal lateinit var sharedpref: SharedPref
    private lateinit var modoOscuro: String

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        sharedpref = SharedPref(this)
        modoOscuro = if(sharedpref.loadNightModeState()==true){
            setTheme(R.style.DarkTheme_WorkingApp)
            "off"
        }else{
            setTheme(R.style.Theme_WorkingApp)
            "on"
        }
        super.onCreate(savedInstanceState)

        bindingClima = ActivityClimaBinding.inflate(layoutInflater)
        setContentView(bindingClima.root)
        val appbarnav = bindingClima.tbClima
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bindingClima.button.setOnClickListener(){
            val intent = Intent(this, PronosticoActivity::class.java)
            startActivity(intent)
        }

        initObserver()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initObserver(){
        viewModelClima.clima.observe(this) {
            if (it.body()!!.cnt > 0) {
                clima = it.body()!!.list[0]
                bindingClima.txtCity.text = it.body()!!.city.name + ", " + it.body()!!.city.country
                bindingClima.txtTemperatura.text = clima.main.temp.toLong().toString()
                bindingClima.txtTemperaturaMax.text = clima.main.tempMax.toLong().toString() + "??"
                bindingClima.txtTemperaturaMin.text = clima.main.tempMin.toLong().toString() + "??"
                bindingClima.txtEstado.text = clima.weather[0].description
                bindingClima.txtViento.text = clima.wind.speed.toString() + " km"
                bindingClima.txtHumedad.text = clima.main.humidity.toString() + "%"
                bindingClima.txtPresion.text = clima.main.pressure.toString()
                bindingClima.txtActCiudad.text =  SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(clima.dt.toLong()*1000))
                bindingClima.txtAmanecer.text = SimpleDateFormat("hh:mm a", Locale.forLanguageTag("es-ES")).format(Date(it.body()!!.city.sunset.toLong()*1000))
                bindingClima.txtAtardecer.text = SimpleDateFormat("hh:mm a", Locale.forLanguageTag("es-ES")).format(Date(it.body()!!.city.sunrise.toLong()*1000))
                bindingClima.txtSTermica.text = clima.main.feelsLike.toLong().toString() + "??"
            }
        }
    }
}
