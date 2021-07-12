package com.example.workingapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.workingapp.data.AppDatabase
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.databinding.ActivityEditBinding


class EditActivity : AppCompatActivity() {
    private val viewModel: TicketViewModel by viewModels {TicketViewModelFactory(applicationContext)}
    private lateinit var bindingEditTicket: ActivityEditBinding
    private var idUpTicket: Long = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingEditTicket = ActivityEditBinding.inflate(layoutInflater)
        setContentView(bindingEditTicket.root)
        var appbarnav = bindingEditTicket.tbTicketEdit
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        updateTicket()
    }

    private fun updateTicket(){
        val idDetalle = intent.getLongExtra("IdTicketEdit", 0)
        idUpTicket = idDetalle
        viewModel.getById(idUpTicket)
        viewModel.ticket.observe(this, Observer{
            bindingEditTicket.etTitulo.setText(it.titulo)
            bindingEditTicket.etAutor.setText(it.autor)
            bindingEditTicket.etDescripcion.setText(it.descripcion)
            idUpTicket = it.id
            bindingEditTicket.btnEditarAceptar.setOnClickListener{
                val ticket = TicketEntity(
                 id = idUpTicket,
                 titulo = bindingEditTicket.etTitulo.text.toString(),
                 autor = bindingEditTicket.etAutor.text.toString(),
                 descripcion= bindingEditTicket.etDescripcion.text.toString())

                viewModel.updateTicket(ticket)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                val database = AppDatabase.getDatabase(this)
                database.ticketDao().update(ticket)

            }

        })
    }


}