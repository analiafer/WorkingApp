package com.example.workingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NewTicketActivity : AppCompatActivity() {
    lateinit var btnSumarAceptar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_ticket)

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
