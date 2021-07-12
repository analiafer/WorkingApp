package com.example.workingapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import com.example.workingapp.R

import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView


import com.example.workingapp.databinding.ActivityDoneBinding


class DoneActivity : AppCompatActivity() {

    private lateinit var bindingDone: ActivityDoneBinding
    private lateinit var viewTicket: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDone = ActivityDoneBinding.inflate(layoutInflater)
        setContentView(bindingDone.root)
        var appbarnav = bindingDone.tbTicket
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setViews()
        setListener()



        /*Ari recycler
        val recycler = findViewById<RecyclerView>(R.id.recycler)
         recycler.adapter =*/
    }

    private fun setViews() {
        viewTicket = findViewById(R.id.viewTicket)

    }

    private fun setListener() {
        viewTicket.setOnClickListener {
            var intent = Intent(this, ViewTicketActivity::class.java)
            startActivity(intent)
        }

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


}


