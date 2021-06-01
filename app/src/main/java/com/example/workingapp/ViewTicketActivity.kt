package com.example.workingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import com.google.android.material.bottomappbar.BottomAppBar

class ViewTicketActivity : AppCompatActivity() {

    lateinit var btnAppBar: BottomAppBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_ticket)
        setSupportActionBar(findViewById(R.id.tbTicketView))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setViews()
    }

    private fun setViews() {
        btnAppBar = findViewById(R.id.barTicketView)
    }

    // Sobre escribo el menú para que sea el que yo creé.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = getMenuInflater()
        inflater.inflate(R.menu.bottom_ticket_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

}