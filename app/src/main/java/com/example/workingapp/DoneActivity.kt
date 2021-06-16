package com.example.workingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workingapp.databinding.ActivityDoneBinding
import com.example.workingapp.recyclerView.Ticket
import com.example.workingapp.recyclerView.TicketAdapter

class DoneActivity : AppCompatActivity(), TicketAdapter.OnTicketClickListener {
    private lateinit var bindingDone: ActivityDoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDone = ActivityDoneBinding.inflate(layoutInflater)
        setContentView(bindingDone.root)
        var appbarnav = bindingDone.tbTicket
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setListener()
        setupRecyclerView()
    }

    private fun setListener() {

        val navigationBottom = bindingDone.bottomNavigation
        navigationBottom.selectedItemId = R.id.option_realizados
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

    private fun setupRecyclerView() {

        val recyclerViewMain = bindingDone.recyclerViewDone
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewMain.layoutManager = layoutManager

        var listTicket = listOf(
            Ticket("Soy un ticket", "Soy una descripcion", "Autor", 1),
            Ticket("Soy un ticket 2", "Soy una descripcion 2", "Autor 2", 2),
            Ticket("Soy un ticket 3", "Soy una descripcion 3", "Autor 3", 3),
            Ticket("Soy un ticket 4", "Soy una descripcion 4", "Autor 4", 4),
            Ticket("Soy un ticket 5", "Soy una descripcion 5", "Autor 5", 5),
            Ticket("Soy un ticket 6", "Soy una descripcion 6", "Autor 6", 6),
            Ticket("Soy un ticket 7", "Soy una descripcion 7", "Autor 7", 7),
            Ticket("Soy un ticket 8", "Soy una descripcion 8", "Autor 8", 8),
            Ticket("Soy un ticket 9", "Soy una descripcion 9", "Autor 9", 9),
            Ticket("Soy un ticket 10", "Soy una descripcion 10", "Autor 10", 10),
            Ticket("Soy un ticket 11", "Soy una descripcion 11", "Autor 11", 11),
            Ticket("Soy un ticket 12", "Soy una descripcion 12", "Autor 12", 12)
        )
        recyclerViewMain.adapter = TicketAdapter(listTicket, this)
        }

        override fun onItemClick() {
            var intent = Intent(this, ViewTicketActivity::class.java)
            startActivity(intent)
        }
    }

