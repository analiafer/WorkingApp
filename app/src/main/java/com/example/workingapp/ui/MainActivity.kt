package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workingapp.R
import com.example.workingapp.data.SharedPref
import com.example.workingapp.databinding.ActivityMainBinding
import com.example.workingapp.model.Ticket
import com.example.workingapp.ui.recyclerView.TicketAdapter
import com.example.workingapp.ui.viewModel.TicketViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), TicketAdapter.OnTicketClickListener {
    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var ticketAdapter: TicketAdapter
    private val viewModel: TicketViewModel by viewModel()

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    internal lateinit var sharedpref: SharedPref
    private lateinit var modoOscuro: String

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        setTheme(R.style.Theme_WorkingApp)

        sharedpref = SharedPref(this)
        modoOscuro = if (sharedpref.loadNightModeState() == true) {
            setTheme(R.style.DarkTheme_WorkingApp)
            "off"
        } else {
            setTheme(R.style.Theme_WorkingApp)
            "on"
        }

        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        setupRecyclerView()
        setListener()
        observer()
        viewModel.getAll()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    fun restartApp() {
        val i = Intent(getApplicationContext(), MainActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun setListener() {
        /*boton Ari para ir al nuevo ticket*/
        bindingMain.btnAddTicket.setOnClickListener {
            val intent = Intent(this, NewTicketActivity::class.java)
            startActivity(intent)
        }

        bindingMain.tbTicket.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.option_celendar -> {
                    startActivity(Intent(this, ClimaActivity::class.java))
                }
                R.id.modoOscuro -> {
                    if (sharedpref.loadNightModeState() == true) {
                        modoOscuro = "off"
                    }
                    if (modoOscuro == "on") {
                        sharedpref.setNightModeState(true)
                        restartApp()
                    } else {
                        sharedpref.setNightModeState(false)
                        restartApp()
                    }
                }
            }
            super.onOptionsItemSelected(item)
        }

        val navigationBottom = bindingMain.bottomNavigation
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
        ticketAdapter = TicketAdapter(this)
        with(bindingMain.recyclerView) {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            this.adapter = this@MainActivity.ticketAdapter
        }
    }

    //Cuando hago click en un item del recycler, voy al ViewTicketActivity y además paso el ID del ticket en el cual estoy apretando
    //para después usarlo en la otra activity.
    override fun onItemClick(ticket: Ticket) {
        val intent = Intent(this, ViewTicketActivity::class.java)
        intent.putExtra("ID", ticket.id)
        startActivity(intent)
    }

    private fun observer() {
        viewModel.ticketLiveData.observe(this) {
            ticketAdapter.submitList(it)
            ticketAdapter.notifyDataSetChanged()
        }
    }

}


