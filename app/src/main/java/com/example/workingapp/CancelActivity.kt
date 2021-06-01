package com.example.workingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workingapp.databinding.ActivityCancelBinding

class CancelActivity : AppCompatActivity() {

    private lateinit var bindingCancel : ActivityCancelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingCancel = ActivityCancelBinding.inflate(layoutInflater)
        setContentView(bindingCancel.root)
        var appbarnav = bindingCancel.tbTicket
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var navigationBottom = bindingCancel.bottomNavigation

        navigationBottom.selectedItemId = R.id.option_cancel

        navigationBottom.setOnNavigationItemSelectedListener{
                item ->
            when(item.itemId) {
                R.id.option_general-> {
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

    private fun optionGeneral(){

        val general = Intent (this, MainActivity::class.java)
        startActivity(general)
    }

    private fun optionRealizado(){

        val realizado = Intent ( this, DoneActivity::class.java)
        startActivity(realizado)
    }

    private fun optionEnProceso(){

        val proceso = Intent (this, EnProcesoActivity::class.java)
        startActivity(proceso)
    }

    private fun optionCancel(){

        val cancel = Intent (this, CancelActivity::class.java)
        startActivity(cancel)
    }

}