package com.example.workingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workingapp.databinding.ActivityCancelBinding
import com.example.workingapp.recyclerView.Ticket
import com.example.workingapp.recyclerView.TicketAdapter

class CancelActivity : AppCompatActivity(), TicketAdapter.OnTicketClickListener {

    private lateinit var bindingCancel: ActivityCancelBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingCancel = ActivityCancelBinding.inflate(layoutInflater)
        setContentView(bindingCancel.root)


        setListener()

        var appbarnav = bindingCancel.tbTicket
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupRecyclerCancel()
    }

    private fun setListener() {
        bindingCancel.viewTicket.setOnClickListener {
            var intent = Intent(this, ViewTicketActivity::class.java)
            startActivity(intent)
        }

        val navigationBottom = bindingCancel.bottomNavigation
        navigationBottom.selectedItemId = R.id.option_cancel
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

    //Setup del recyclerCancel
    private fun setupRecyclerCancel() {
        //Asigno una variable al recyclerView del layout

        val recyclerCancelMain = bindingCancel.recyclerCancel
        //Asigno un layoutManager para elegir de que manera va a organizarse el recycler (grid, linear, etc...)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //Le termino de asignar la variable anterior al layoutManager
        recyclerCancelMain.layoutManager = layoutManager

        //Sólo de prueba al no contar aún con room, creo los datos manualmente.
        var listTicket2 = listOf(
                Ticket("Soy un ticket", "Soy una descripcion", "Autor", 1),
                Ticket("Soy un ticket 2", "Soy una descripcion 2", "Autor 2", 2),
                Ticket("Soy un ticket 3", "Soy una descripcion 3", "Autor 3", 3)
        )
        recyclerCancelMain.adapter = TicketAdapter(listTicket2, this)
    }

    //Le doy la funcionalidad a la función de la interface.
    override fun onItemClick() {
        var intent = Intent(this, ViewTicketActivity::class.java)
    }
}
