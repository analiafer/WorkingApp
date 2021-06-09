package com.example.workingapp


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workingapp.service.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Clima : AppCompatActivity() {

    var accessKey: String = "7a9237f486a2764a3e71ad428f1987f0"
    var ciudad: String = "Londres"
    lateinit var txtCity: TextView
    lateinit var ivActualizar: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clima)

        getViews()


        MyToolbar().show(this, "Clima", true)

    }

    private fun getViews() {
        txtCity = findViewById(R.id.txtCity)
        ivActualizar = findViewById(R.id.imgButtonAct)
    }

    private fun setListeners(){
        ivActualizar.setOnClickListener{
            getCurrentData()
        }
    }
   private fun getCurrentData() {
       API().getCurrentWeatherData(accessKey, ciudad, object : Callback<WeatherResponse?>{
           override fun onResponse(call: Call<WeatherResponse?>, response: Response<WeatherResponse?>) {
               if (response.isSuccessful){
                   response.body()!!.apply {
                        txtCity.text = this.location.country
                   }
               }else{
                   Toast.makeText(this@Clima, "Fallo con codigo ${response.code()}", Toast.LENGTH_LONG).show()
               }
           }

           override fun onFailure(call: Call<WeatherResponse?>, t: Throwable) {
               Log.e("MainActivity", "Fallo al obtener datos", t)
           }
           })
   }


}


