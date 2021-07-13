package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workingapp.*
import com.example.workingapp.model.Ticket
import com.example.workingapp.ui.recyclerView.TicketAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import android.widget.Switch
import com.example.workingapp.data.SharedPref
import com.example.workingapp.databinding.ActivityCancelBinding
import com.example.workingapp.databinding.ActivityMainBinding


class CancelActivity : AppCompatActivity(), TicketAdapter.OnTicketClickListener{

    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var bindingCancel: ActivityCancelBinding
    private lateinit var ticketAdapter : TicketAdapter
    private val cancelVm : CancelViewModel by viewModel()


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
        bindingCancel = ActivityCancelBinding.inflate(layoutInflater)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bindingCancel.root)

        val appbarnav = bindingCancel.tbTicket
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setListener()
        setupRecycler()
        observer()
    }

    override fun onResume(){
        super.onResume()
        cancelVm.getAllCancel()
    }

    private fun setListener() {

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

    private fun setupRecycler(){
        ticketAdapter = TicketAdapter(this)
        with(bindingCancel.recyclerCancel){
            layoutManager = LinearLayoutManager(this@CancelActivity,LinearLayoutManager.VERTICAL, false)
            this.adapter = this@CancelActivity.ticketAdapter
        }
    }

    override fun onItemClick(ticket: Ticket) {
        val intentCancel = Intent(this, ViewTicketActivity:: class.java)
        intentCancel.putExtra("ID", ticket.id)
        startActivity(intentCancel)
    }

    private fun observer(){
        cancelVm.cancelLiveData.observe(this, Observer {
            ticketAdapter.submitList(it)
            ticketAdapter.notifyDataSetChanged()
        })
    }

}
