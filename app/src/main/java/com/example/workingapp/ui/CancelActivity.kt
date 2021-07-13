package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import com.example.workingapp.*
import com.example.workingapp.data.SharedPref

import com.example.workingapp.databinding.ActivityCancelBinding
import com.example.workingapp.databinding.ActivityMainBinding


class CancelActivity : AppCompatActivity(){

    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var bindingCancel: ActivityCancelBinding

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

        var appbarnav = bindingCancel.tbTicket
        setSupportActionBar(appbarnav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setListener()
    }

    fun restartApp(){
        val i = Intent(getApplicationContext(), MainActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun setListener() {
       /* bindingCancel.viewTicket.setOnClickListener {
            var intent = Intent(this, ViewTicketActivity::class.java)
            startActivity(intent)
        }*/

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



}
