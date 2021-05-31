package com.example.workingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var btnViewTicket: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setViews()
        setListeners()

    }

    private fun setViews() {
        btnViewTicket = findViewById(R.id.btnViewTicket)
    }

    private fun setListeners() {
        btnViewTicket.setOnClickListener {
            navigateToTicket()
        }
    }

    private fun navigateToTicket() {
        val intent = Intent(this, ViewTicketActivity::class.java)
        startActivity(intent)
    }

}