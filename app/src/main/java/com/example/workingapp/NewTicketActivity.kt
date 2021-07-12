package com.example.workingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.workingapp.databinding.ActivityDoneBinding
import com.example.workingapp.databinding.ActivityNewTicketBinding

class NewTicketActivity : AppCompatActivity() {
    lateinit var btnSumarAceptar: Button
    private lateinit var bindingNewTicket: ActivityNewTicketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    bindingNewTicket = ActivityNewTicketBinding.inflate(layoutInflater)
        setContentView(bindingNewTicket.root)

        getViews()
        setListeners()
    }
    private fun getViews(){
        bindingNewTicket.btnSumarAceptar
    }

    private fun setListeners(){
        bindingNewTicket.btnSumarAceptar
    }
}
