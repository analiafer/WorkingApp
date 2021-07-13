package com.example.workingapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.workingapp.R
import com.example.workingapp.data.SharedPref
import com.example.workingapp.databinding.ActivityNewTicketBinding
import com.example.workingapp.ui.viewModel.AddTicketViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class NewTicketActivity : AppCompatActivity() {
    private lateinit var bindingNewTicket : ActivityNewTicketBinding
    private val vm: AddTicketViewModel by viewModel()

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    internal lateinit var sharedpref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedpref = SharedPref(this)
        if(sharedpref.loadNightModeState()==true){
            setTheme(R.style.DarkTheme_WorkingApp)
        }else{
            setTheme(R.style.Theme_WorkingApp)
        }

        super.onCreate(savedInstanceState)

        bindingNewTicket = ActivityNewTicketBinding.inflate(layoutInflater)
        setContentView(bindingNewTicket.root)

        val appbarnav = bindingNewTicket.tbTicket
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        addDataToDatabase()
    }

    private fun addDataToDatabase(){

        bindingNewTicket.btnSumarAceptar.setOnClickListener {

            val titulo = bindingNewTicket.etTitulo.text.toString()
            val autor = bindingNewTicket.etAutor.text.toString()
            val contenido = bindingNewTicket.etDescripcion.text.toString()

            if(titulo.isEmpty() || autor.isEmpty() || contenido.isEmpty()){
                Toast.makeText(this, R.string.toastInfoVacia, Toast.LENGTH_SHORT).show()

            }else{
                vm.saveTicket(titulo, autor, contenido)
                finish()
            }

        }

    }
}
