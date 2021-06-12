package com.example.workingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import com.example.workingapp.databinding.ActivityViewTicketBinding
import com.google.android.material.bottomappbar.BottomAppBar

class ViewTicketActivity : AppCompatActivity() {
    private lateinit var bindingViewTicketActivity: ActivityViewTicketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingViewTicketActivity = ActivityViewTicketBinding.inflate(layoutInflater)
        setContentView(bindingViewTicketActivity.root)
        setContentView(R.layout.activity_view_ticket)
        setSupportActionBar(findViewById(R.id.tbTicketView))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Sobre escribo el menú para que sea el que yo creé.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = getMenuInflater()
        inflater.inflate(R.menu.bottom_ticket_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

}