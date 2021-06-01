package com.example.workingapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.workingapp.service.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Clima : AppCompatActivity() {

    lateinit var txtCity: TextView
    var ciudad: String = "Buenos Aires"
    val API: String = "c0d4c0df755521b2be43fd8bf65f2791"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clima)

        txtCity = findViewById(R.id.txtCity)


        MyToolbar().show(this, "Clima", true)

        searchLocation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_clima, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.search_city){
            val search: EditText = findViewById(R.id.txtBuscar)
            val buscar: ImageButton = findViewById(R.id.imgButtonSearch)

            if (search.visibility==View.INVISIBLE){
                search.visibility = View.VISIBLE
                buscar.visibility = View.VISIBLE
            }
        }
        return super.onOptionsItemSelected(item)
    }

   private fun searchLocation(){
       API().getLocation(ciudad, object : Callback<location>{
           override fun onResponse(call: Call<location>, response: Response<location>) {
               if(response.isSuccessful){
                   response.body()!!.apply {
                       txtCity.text = this.name + ", " + this.country
                   }
               }else{
                   Toast.makeText(this@Clima, "Fallo con codigo ${response.code()}", Toast.LENGTH_LONG).show()
               }
           }

           override fun onFailure(call: Call<location>, t: Throwable) {
               Log.e("MainActivity", "Fallo al obtener datos", t)
           }
       })
    }



}
