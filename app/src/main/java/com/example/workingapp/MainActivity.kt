package com.example.workingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        setListener()
    }

    private fun setListener() {
        /*botón para ir al ticket*/
        bindingMain.viewTicket.setOnClickListener {
            var intent = Intent(this, ViewTicketActivity::class.java)
            startActivity(intent)
        }

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
}


