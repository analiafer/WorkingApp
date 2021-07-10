package com.example.workingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.workingapp.R
import com.example.workingapp.databinding.ActivityNewTicketBinding

class NewTicketActivity : AppCompatActivity() {
    private lateinit var bindingNewTicket : ActivityNewTicketBinding
    private val vm: TicketViewModel by viewModels { TicketViewModelFactory(applicationContext)  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingNewTicket = ActivityNewTicketBinding.inflate(layoutInflater)
        setContentView(bindingNewTicket.root)

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
