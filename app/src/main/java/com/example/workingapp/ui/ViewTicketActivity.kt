package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Switch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.workingapp.R
import com.example.workingapp.data.SharedPref
import com.example.workingapp.databinding.ActivityMainBinding
import com.example.workingapp.databinding.ActivityViewTicketBinding


class ViewTicketActivity : AppCompatActivity() {
    private lateinit var bindingViewTicket: ActivityViewTicketBinding
    private lateinit var bindingMain: ActivityMainBinding

    private val viewModel: TicketViewModel by viewModels {TicketViewModelFactory(applicationContext)}
    private var idTicket: Long = 0
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
        bindingViewTicket = ActivityViewTicketBinding.inflate(layoutInflater)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bindingViewTicket.root)
        //Guardamos en una variable los datos del ID del Ticket pasados desde la otra activity.
        val idDetalle = intent.getLongExtra("ID", 0)
        //Ese mismo número lo seteamos a otra vareable, que la pasamos por el metodo para que el viewModel obtenga el ticket via la función.
        idTicket = idDetalle
        viewModel.getById(idTicket)
        setObserver()
        setListener()
        bindingViewTicket.btnEditTicket.setOnClickListener(){
            val intentEdit = Intent(this, EditActivity::class.java)
            intentEdit.putExtra("IdTicketEdit",idTicket)
            startActivity(intentEdit)
        }

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

    //función que setea los datos en la base de datos con los elementos del activity.
    private fun setObserver() {
        viewModel.ticket.observe(this, Observer {
            bindingViewTicket.textTituloTicket.text = it.titulo
            bindingViewTicket.textNombreAutor.text = it.autor
            bindingViewTicket.textDescripcionTicket.text = it.descripcion
            bindingViewTicket.textDateTicket.text = it.fechahora
        })
    }

    //función que se encarga del comportamiento de los botones de la barra.
    //Uno borra el ticket, el otro vuelve atrás.
    private fun setListener() {
        bindingViewTicket.tbTicketView.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.optionDeleteTicket) {
                viewModel.ticket.removeObservers(this)
                viewModel.delete(viewModel.ticket.value!!)
                finish()
            } else finish()
            super.onOptionsItemSelected(item)
        }
    }
}
