package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.workingapp.R
import com.example.workingapp.Working
import com.example.workingapp.data.AppDatabase
import com.example.workingapp.data.SharedPref
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.databinding.ActivityEditBinding
import com.example.workingapp.ui.viewModel.TicketViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.text.SimpleDateFormat
import java.util.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.ext.scope


class EditActivity : AppCompatActivity() {
    private val viewModel: EditActivityViewModel by viewModel()
    private lateinit var bindingEditTicket: ActivityEditBinding
    private var idUpTicket: Long = 0

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    internal lateinit var sharedpref: SharedPref
    private lateinit var modoOscuro: String

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedpref = SharedPref(this)
        if(sharedpref.loadNightModeState()==true){
            setTheme(R.style.DarkTheme_WorkingApp)
        }else{
            setTheme(R.style.Theme_WorkingApp)
        }

        super.onCreate(savedInstanceState)
        bindingEditTicket = ActivityEditBinding.inflate(layoutInflater)
        setContentView(bindingEditTicket.root)
        var appbarnav = bindingEditTicket.tbTicketEdit
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        updateTicket()
    }

    @SuppressLint("SimpleDateFormat")
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
                 descripcion= bindingEditTicket.etDescripcion.text.toString(),
                 fechahora = SimpleDateFormat("dd.MM.yyyy HH:mm").format(Date())
                )

                viewModel.updateTicket(ticket)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                GlobalScope.launch {
                    var working = Working()
                    val database = working.dataBase(this@EditActivity)
                    database.ticketDao().update(ticket)
                }


            }

        })
    }


}