package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.workingapp.R
import com.example.workingapp.Working
import com.example.workingapp.data.AppDatabase
import com.example.workingapp.data.SharedPref
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.databinding.ActivityEditBinding
import com.example.workingapp.ui.viewModel.EditActivityViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import org.koin.android.viewmodel.ext.android.viewModel

class EditActivity : AppCompatActivity() {
    private val viewModel: EditActivityViewModel by viewModel()
    private lateinit var bindingEditTicket: ActivityEditBinding
    private var idUpTicket: Long = 0
    private var estadoTicket : String = " "


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    internal lateinit var sharedpref: SharedPref


    override fun onCreate(savedInstanceState: Bundle?) {

        sharedpref = SharedPref(this)
        if (sharedpref.loadNightModeState() == true) {
            setTheme(R.style.DarkTheme_WorkingApp)
        } else {
            setTheme(R.style.Theme_WorkingApp)
        }

        super.onCreate(savedInstanceState)
        bindingEditTicket = ActivityEditBinding.inflate(layoutInflater)
        setContentView(bindingEditTicket.root)
        val appbarnav = bindingEditTicket.tbTicketEdit
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        updateTicket()
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {

            val checked = view.isChecked

            when (view.getId()) {
                R.id.ticketRealizado ->
                    if (checked) {
                        estadoTicket = "realizado"
                    }
                R.id.ticket_enProceso ->
                    if (checked) {
                        estadoTicket = "en proceso"
                    }
                R.id.ticket_cancelado ->
                    if (checked) {
                        estadoTicket = "cancelado"
                    }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateTicket() {

        val idDetalle = intent.getLongExtra("IdTicketEdit", 0)
        idUpTicket = idDetalle

        viewModel.getById(idUpTicket)
        viewModel.ticket.observe(this, Observer {
            bindingEditTicket.etTitulo.setText(it.titulo)
            bindingEditTicket.etAutor.setText(it.autor)
            bindingEditTicket.etDescripcion.setText(it.descripcion)
            idUpTicket = it.id

            bindingEditTicket.btnEditarAceptar.setOnClickListener {
                val ticket = TicketEntity(
                        id = idUpTicket,
                        titulo = bindingEditTicket.etTitulo.text.toString(),
                        autor = bindingEditTicket.etAutor.text.toString(),
                        descripcion = bindingEditTicket.etDescripcion.text.toString(),
                        fechahora = SimpleDateFormat("dd.MM.yyyy HH:mm").format(Date()),
                        estado = estadoTicket
                )
                val titulo = bindingEditTicket.etTitulo.text.toString()
                val autor = bindingEditTicket.etAutor.text.toString()
                val contenido = bindingEditTicket.etDescripcion.text.toString()

                if (titulo.isEmpty() || autor.isEmpty() || contenido.isEmpty()) {
                    Toast.makeText(this, R.string.toastInfoVacia, Toast.LENGTH_SHORT).show()
                } else {

                    viewModel.updateTicket(ticket)
                    GlobalScope.launch {
                        val working = Working()
                        val database = working.dataBase(this@EditActivity)
                        database.ticketDao().update(ticket)
                    }
                    finish()
                }

            }
        })
    }
}

