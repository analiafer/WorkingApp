package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.example.workingapp.R
import com.example.workingapp.data.SharedPref
import com.example.workingapp.databinding.ActivityPronosticoClimaBinding
import com.example.workingapp.model.Pronostico
import com.example.workingapp.model.WeatherList
import com.example.workingapp.model.WeatherModel
import com.example.workingapp.ui.ClimaViewModel
import com.example.workingapp.ui.recyclerView.PronosticoAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class PronosticoActivity : AppCompatActivity(){
    private lateinit var bindingPronostico: ActivityPronosticoClimaBinding
    private val viewModelClima: ClimaViewModel by viewModel()
    private lateinit var clima: WeatherList
    private lateinit var pronosticoAdapter: PronosticoAdapter


    private val variableFecha: ArrayList<String> = arrayListOf<String>()
    private val variableDia: ArrayList<String> = arrayListOf<String>()
    private val numbers = arrayOf("8", "16", "24", "32")

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

        bindingPronostico = ActivityPronosticoClimaBinding.inflate(layoutInflater)
        setContentView(bindingPronostico.root)

        val appbarnav = bindingPronostico.tbPronostico
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupRecyclerView()
        initObserver()
    }

    //Setup del recyclerView
    private fun setupRecyclerView() {
        pronosticoAdapter = PronosticoAdapter()
        with(bindingPronostico.recyclerViewPronostico) {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this@PronosticoActivity,
                    androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                    false)
            this.adapter = this@PronosticoActivity.pronosticoAdapter
        }
    }

    //inicio observer
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initObserver() {
        viewModelClima.clima.observe(this) {
            loadDates(it)
            loadValueRecycler(it)
        }
    }

    //cargo dia y fechas para el recycler
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun loadDates(weatherModel: Response<WeatherModel>){
        for ((i, element) in numbers.withIndex()) {
            clima = weatherModel.body()!!.list[element.toInt()]
            variableFecha.add(
                SimpleDateFormat("dd/M/yyyy",
                    Locale.ENGLISH).format(Date(clima.dt.toLong()*1000)))
            variableDia.add(
                SimpleDateFormat("EEEE",
                    Locale.forLanguageTag("es-ES")).format(
                    SimpleDateFormat("dd/MM/yyyy").parse(variableFecha[i]))
                    .capitalize())
        }
    }

    //cargo valores recycler
    private fun loadValueRecycler(weatherModel: Response<WeatherModel>){
        if (weatherModel.body()!!.cnt > 0) {
            for ((i, element) in numbers.withIndex()) {
                clima = weatherModel.body()!!.list[element.toInt()]
                pronosticoAdapter.pronosticoList.add(Pronostico(variableDia[i],
                    clima.weather[0].description.capitalize(),
                    clima.wind.speed.toString() + " km/h",
                    "Max: " + clima.main.tempMax.toLong().toString() + "°",
                    "Min: " + clima.main.tempMin.toLong().toString() + "°",
                    variableFecha[i],"http://openweathermap.org/img/wn/${clima.weather[0].icon}@2x.png"))
            }
            pronosticoAdapter.notifyDataSetChanged()
        }
    }

}
