package com.example.workingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import com.example.workingapp.R
import com.example.workingapp.databinding.ActivityViewTicketBinding


class ViewTicketActivity : AppCompatActivity() {
    private lateinit var bindingViewTicket: ActivityViewTicketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingViewTicket = ActivityViewTicketBinding.inflate(layoutInflater)
        setContentView(bindingViewTicket.root)
        setSupportActionBar(bindingViewTicket.tbTicketView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Sobre escribo el menú para que sea el que yo creé.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = getMenuInflater()
        inflater.inflate(R.menu.bottom_ticket_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

}