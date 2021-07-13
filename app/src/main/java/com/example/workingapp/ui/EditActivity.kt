package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.workingapp.R
import com.example.workingapp.data.AppDatabase
import com.example.workingapp.data.SharedPref
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.databinding.ActivityEditBinding
import com.example.workingapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


class EditActivity : AppCompatActivity() {
    private val viewModel: TicketViewModel by viewModels {TicketViewModelFactory(applicationContext)}
    private lateinit var bindingEditTicket: ActivityEditBinding
    private lateinit var bindingMain: ActivityMainBinding

    private var idUpTicket: Long = 0

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private var xyz: Switch? = null
    internal lateinit var sharedpref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedpref = SharedPref(this)
        if(sharedpref.loadNightModeState()==true){
            setTheme(R.style.DarkTheme_WorkingApp)
        }else{
            setTheme(R.style.Theme_WorkingApp)
        }

        super.onCreate(savedInstanceState)
        bindingEditTicket = ActivityEditBinding.inflate(layoutInflater)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bindingEditTicket.root)
        var appbarnav = bindingEditTicket.tbTicketEdit
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        updateTicket()
    }

    fun restartApp(){
        val i = Intent(getApplicationContext(), MainActivity::class.java)
        startActivity(i)
        finish()
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
                val database = AppDatabase.getDatabase(this)
                database.ticketDao().update(ticket)

            }

        })
    }


}