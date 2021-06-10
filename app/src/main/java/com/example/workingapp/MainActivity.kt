package com.example.workingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.workingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindingmain : ActivityMainBinding

    /* boton para agregar ticket Ari*/
    lateinit var btn_addTicket: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingmain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingmain.root)

        /* boton para agregar ticket Ari*/
        btn_addTicket = findViewById(R.id.btn_addTicket)
        btn_addTicket.setOnClickListener {
            val intent = Intent(this, sumar::class.java)
            startActivity(intent)
        }


        var navigationBottom = bindingmain.bottomNavigation

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


