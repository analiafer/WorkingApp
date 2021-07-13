package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workingapp.R
import com.example.workingapp.data.SharedPref
import com.example.workingapp.databinding.ActivityDoneBinding
import com.example.workingapp.model.Ticket
import com.example.workingapp.ui.recyclerView.TicketAdapter
import com.example.workingapp.ui.viewModel.DoneViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class DoneActivity : AppCompatActivity(), TicketAdapter.OnTicketClickListener {

    private lateinit var bindingDone: ActivityDoneBinding
    private lateinit var ticketAdapter : TicketAdapter
    private val doneVm : DoneViewModel by viewModel()

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
        bindingDone = ActivityDoneBinding.inflate(layoutInflater)
        setContentView(bindingDone.root)
        val appbarnav = bindingDone.tbTicket
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setListener()
        setupRecycler()
        observer()
        doneVm.getAllDone()
    }

    override fun onResume() {
        super.onResume()
        doneVm.getAllDone()
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

    private fun setupRecycler(){
        ticketAdapter = TicketAdapter(this)
        with(bindingDone.recycler){
          layoutManager = LinearLayoutManager(this@DoneActivity, LinearLayoutManager.VERTICAL, false)
          this.adapter = this@DoneActivity.ticketAdapter
        }
    }

    override fun onItemClick(ticket: Ticket) {
        val intentDone = Intent (this, ViewTicketActivity::class.java)
        intentDone.putExtra("ID", ticket.id)
        startActivity(intentDone)
    }

    private fun observer(){
        doneVm.doneLiveData.observe(this, Observer {
            ticketAdapter.submitList(it)
            ticketAdapter.notifyDataSetChanged()
        })
    }

}


