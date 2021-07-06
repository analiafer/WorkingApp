package com.example.workingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workingapp.data.TicketViewModel
import com.example.workingapp.databinding.ActivityMainBinding
import com.example.workingapp.databinding.ActivityNewTicketBinding
import com.example.workingapp.recyclerView.Ticket
import com.example.workingapp.recyclerView.TicketAdapter

class MainActivity : AppCompatActivity(), TicketAdapter.OnTicketClickListener {
    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var viewModel: TicketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        viewModel = ViewModelProvider(this).get(TicketViewModel::class.java)
        setupRecyclerView()
        setListener()
    }

    private fun setListener() {
        /*boton Ari para ir al nuevo ticket*/
        bindingMain.btnAddTicket.setOnClickListener {
            val intent = Intent(this, NewTicketActivity::class.java)
            startActivity(intent)
        }

        bindingMain.tbTicket.setOnMenuItemClickListener{ item ->
            if (item.itemId==R.id.option_celendar) startActivity(Intent(this, ClimaActivity::class.java))
             super.onOptionsItemSelected(item)
        }

        var navigationBottom = bindingMain.bottomNavigation
        navigationBottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.option_general -> {
                    optionGeneral()
                    true
                }
                R.id.option_realizados -> {
                    optionRealizado()
                    true
                }

                R.id.option_enproceso -> {
                    optionEnProceso()
                    true
                }

                R.id.option_cancel -> {
                    optionCancel()
                    true
                }
                else -> {
                    super.onOptionsItemSelected(item)
                }
            }

        }

    }

    private fun optionGeneral() {
        val general = Intent(this, MainActivity::class.java)
        startActivity(general)
    }

    private fun optionRealizado() {

        val realizado = Intent(this, DoneActivity::class.java)
        startActivity(realizado)
    }

    private fun optionEnProceso() {

        val proceso = Intent(this, EnProcesoActivity::class.java)
        startActivity(proceso)
    }

    private fun optionCancel() {

        val cancel = Intent(this, CancelActivity::class.java)
        startActivity(cancel)
    }

    //Setup del recyclerView
    private fun setupRecyclerView() {
        //Asigno una variable al recyclerView del layout
        val recyclerViewMain = bindingMain.recyclerView
        //Asigno un layoutManager para elegir de que manera va a organizarse el recycler (grid, linear, etc...)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //Le termino de asignar la variable anterior al layoutManager
        recyclerViewMain.layoutManager = layoutManager

        //Sólo de prueba al no contar aún con room, creo los datos manualmente.
        var listTicket = viewModel.getAll() //<< Hice una función para traer los datos a una lista
        recyclerViewMain.adapter = TicketAdapter(listTicket, this) //<< LiveData
    }

    //Le doy la funcionalidad a la función de la interface.
    override fun onItemClick() {
        var intent = Intent(this, ViewTicketActivity::class.java)
        startActivity(intent)
    }
}


