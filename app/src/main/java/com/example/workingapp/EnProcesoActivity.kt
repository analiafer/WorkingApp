package com.example.workingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workingapp.databinding.ActivityEnProcesoBinding
import com.example.workingapp.recyclerView.Ticket
import com.example.workingapp.recyclerView.TicketAdapter

class EnProcesoActivity : AppCompatActivity(), TicketAdapter.OnTicketClickListener {
    private lateinit var bindingProceso: ActivityEnProcesoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingProceso = ActivityEnProcesoBinding.inflate(layoutInflater)
        setContentView(bindingProceso.root)
        setupRecyclerView()
        val appbarnav = bindingProceso.tbTicket
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setListener()
    }

    private fun setListener() {
        bindingProceso.recyclerView.setOnClickListener{
            val intent = Intent(this, ViewTicketActivity::class.java)
            startActivity(intent)
        }

        val navigationBottom = bindingProceso.bottomNavigation
        navigationBottom.selectedItemId = R.id.option_enproceso
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
        val recyclerViewMain = bindingProceso.recyclerView
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewMain.layoutManager = layoutManager

        var listTicket = listOf(
            Ticket("Ticket 1", "desc", "Autor", 1),
            Ticket("Ticket 2", "desc", "Autor 2", 2),
            Ticket("Ticket 3", "desc", "Autor 3", 3),
            Ticket("Ticket 4", "desc", "Autor 4", 4),
            Ticket("Ticket 5", "desc", "Autor 5", 5),
            Ticket("Ticket 6", "desc", "Autor 6", 6),
            Ticket("Ticket 7", "desc", "Autor 7", 7),
            Ticket("Ticket 8", "desc", "Autor 8", 8)
        )
        recyclerViewMain.adapter = TicketAdapter(listTicket, this)
    }

    override fun onItemClick() {
        var intent = Intent(this, ViewTicketActivity::class.java)
        startActivity(intent)
    }

}
