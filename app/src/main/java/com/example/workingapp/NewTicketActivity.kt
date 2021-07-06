package com.example.workingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.data.TicketViewModel
import com.example.workingapp.databinding.ActivityNewTicketBinding

class NewTicketActivity : AppCompatActivity() {
    private lateinit var bindingNewTicket: ActivityNewTicketBinding
    private lateinit var viewModel: TicketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingNewTicket = ActivityNewTicketBinding.inflate(layoutInflater)
        setContentView(bindingNewTicket.root)
       viewModel = ViewModelProvider(this).get(TicketViewModel::class.java)


        setListeners()
    }

    private fun setListeners() {
        bindingNewTicket.btnSumarAceptar.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val titulo = bindingNewTicket.etTitulo.text.toString()
        val descripcion = bindingNewTicket.etDescripcion.text.toString()
        val autor = bindingNewTicket.etAutor.text.toString()

        if(inputCheck(titulo, descripcion, autor)){
            //Crear ticket
            val ticket = TicketEntity(0, titulo, descripcion, autor)
            //Añadirlo a la base de datos
            viewModel.addTicket(ticket)
            finish()
        }
    }

    private fun inputCheck(titulo: String, descripcion: String, autor: String): Boolean {
        return !(TextUtils.isEmpty(titulo) && TextUtils.isEmpty(descripcion))
    }
}
