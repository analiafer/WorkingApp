package com.example.workingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class sumar : AppCompatActivity() {
    lateinit var btnSumarAceptar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sumar)

        getViews()
        setListeners()
    }

    private fun getViews(){
        btnSumarAceptar = findViewById(R.id.btnSumarAceptar)
    }

    private fun setListeners(){
        btnSumarAceptar.setOnClickListener {
            finish()
        }
    }
}