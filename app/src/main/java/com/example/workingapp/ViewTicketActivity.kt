package com.example.workingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class ViewTicketActivity : AppCompatActivity() {
    lateinit var btnCloseTicket: Button
    lateinit var btnDeleteTicket: Button
    lateinit var btnEditTicket: Button
    lateinit var btnBack: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_ticket)

        setViews()
        setListeners()

    }

    private fun setViews() {
        btnBack = findViewById(R.id.buttonBackArrow)
    }

    private fun setListeners() {
        btnBack.setOnClickListener {
            finish()
        }
    }
}