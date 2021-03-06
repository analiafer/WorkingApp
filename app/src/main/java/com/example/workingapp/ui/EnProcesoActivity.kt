package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workingapp.R
import com.example.workingapp.data.SharedPref
import com.example.workingapp.databinding.ActivityEnProcesoBinding
import com.example.workingapp.model.Ticket
import com.example.workingapp.ui.recyclerView.TicketAdapter
import com.example.workingapp.ui.viewModel.EnProcesoViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class EnProcesoActivity : AppCompatActivity(), TicketAdapter.OnTicketClickListener {
    private lateinit var bindingProceso: ActivityEnProcesoBinding
    private lateinit var ticketAdapter : TicketAdapter
    private val procesoVm : EnProcesoViewModel by viewModel()



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
        bindingProceso = ActivityEnProcesoBinding.inflate(layoutInflater)
        setContentView(bindingProceso.root)
        val appbarnav = bindingProceso.tbTicket
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setListener()
        setupRecycler()
        observer()

    }

    override fun onResume() {
        super.onResume()
        procesoVm.getAllEnProceso()
    }


    private fun setListener() {

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
                    true
                }

                R.id.option_cancel -> {
                    optionCancel()
                    true
                }

                R.id.option_user -> {
                    optionUser()
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
        finish()
    }

    private fun optionRealizado() {

        val realizado = Intent(this, DoneActivity::class.java)
        startActivity(realizado)
        finish()
    }

    private fun optionCancel() {

        val cancel = Intent(this, CancelActivity::class.java)
        startActivity(cancel)
        finish()
    }

    private fun optionUser(){
        val user = Intent (this, UserActivity::class.java)
        startActivity(user)
    }

    private fun setupRecycler(){
        ticketAdapter = TicketAdapter(this)
        with(bindingProceso.recyclerView){
            layoutManager = LinearLayoutManager(this@EnProcesoActivity, LinearLayoutManager.VERTICAL, false)
            this.adapter = this@EnProcesoActivity.ticketAdapter
        }
    }

    override fun onItemClick(ticket: Ticket) {
        val intentProceso = Intent(this, ViewTicketActivity:: class.java)
        intentProceso.putExtra("ID", ticket.id)
        startActivity(intentProceso)
    }

    private fun observer(){
        procesoVm.procesoLiveData.observe(this, Observer {
            ticketAdapter.submitList(it)
            ticketAdapter.notifyDataSetChanged()
        })
    }

}
