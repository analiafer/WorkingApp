package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.activity.viewModels
import com.example.workingapp.R
import com.example.workingapp.data.SharedPref
import com.example.workingapp.databinding.ActivityMainBinding
import com.example.workingapp.databinding.ActivityNewTicketBinding

class NewTicketActivity : AppCompatActivity() {
    private lateinit var bindingNewTicket : ActivityNewTicketBinding
    private lateinit var bindingMain: ActivityMainBinding

    private val vm: TicketViewModel by viewModels { TicketViewModelFactory(applicationContext)  }

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

        bindingNewTicket = ActivityNewTicketBinding.inflate(layoutInflater)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bindingNewTicket.root)

        val appbarnav = bindingNewTicket.tbTicket
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        addDataToDatabase()

        xyz = bindingMain.switchModo as Switch?
        if (sharedpref.loadNightModeState() == true){
            xyz!!.isChecked = true
        }
        xyz!!.setOnCheckedChangeListener{
                buttonView, isChecked ->
            if(isChecked){
                sharedpref.setNightModeState(true)
                restartApp()
            }else{
                sharedpref.setNightModeState(false)
                restartApp()
            }
        }
    }

    fun restartApp(){
        val i = Intent(getApplicationContext(), MainActivity::class.java)
        startActivity(i)
        finish()
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
