package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
=======
import android.widget.LinearLayout
>>>>>>> d423fb6c325eafe7761c3aab2ee1e3980de30a49
import com.example.workingapp.R
import com.example.workingapp.data.SharedPref
import com.example.workingapp.databinding.ActivityEnProcesoBinding
import com.example.workingapp.model.Ticket
import com.example.workingapp.ui.recyclerView.TicketAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class EnProcesoActivity : AppCompatActivity(), TicketAdapter.OnTicketClickListener {
    private lateinit var bindingProceso: ActivityEnProcesoBinding
<<<<<<< HEAD
    private lateinit var ticketAdapter : TicketAdapter
    private val procesoVm : EnProcesoViewModel by viewModel()
=======
    private lateinit var viewTicket: LinearLayout

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    internal lateinit var sharedpref: SharedPref
>>>>>>> d423fb6c325eafe7761c3aab2ee1e3980de30a49

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
<<<<<<< HEAD
        setupRecycler()
        observer()

    }

    override fun onResume() {
        super.onResume()
        procesoVm.getAllEnProceso()
    }

    private fun setListener() {

=======
    }

    private fun setListener() {
       /* bindingProceso.viewTicket.setOnClickListener {
            var intent = Intent(this, ViewTicketActivity::class.java)
            startActivity(intent)
        }
*/
>>>>>>> d423fb6c325eafe7761c3aab2ee1e3980de30a49
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
<<<<<<< HEAD

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
=======
>>>>>>> d423fb6c325eafe7761c3aab2ee1e3980de30a49
}
