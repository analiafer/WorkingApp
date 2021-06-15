package com.example.workingapp


import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ClimaActivity : AppCompatActivity() {

    var accessKey: String ="7a9237f486a2764a3e71ad428f1987f0"
    var ciudad: String = "Londres"
    lateinit var txtCity: TextView
    lateinit var ivActualizar: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clima)
    }

}
