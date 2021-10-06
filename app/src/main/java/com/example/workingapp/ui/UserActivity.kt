package com.example.workingapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.workingapp.R
import com.example.workingapp.data.Preferences
import com.example.workingapp.data.SharedPref
import com.example.workingapp.databinding.ActivityUserBinding
import com.example.workingapp.ui.viewModel.UserViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class UserActivity: AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var txtUserName: TextView
    private lateinit var btnCerrarSesion: Button
    private val userViewModel: UserViewModel by viewModel()
    private lateinit var sharedPref: SharedPreferences

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    internal lateinit var sharedprefDark: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedprefDark = SharedPref(this)
        if (sharedprefDark.loadNightModeState() == true) {
            setTheme(R.style.DarkTheme_WorkingApp)
        } else {
            setTheme(R.style.Theme_WorkingApp)
        }
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(LayoutInflater.from(this))
        sharedPref = this.getSharedPreferences("FILE_PREFERENCES_USER_ID", Context.MODE_PRIVATE)
        setContentView(binding.root)
        getViews()
        setUserName()
        setListeners()
    }

    private fun getViews() {
        btnCerrarSesion = binding.btnCerrarSesion
        txtUserName = binding.txtUserName
    }

    private fun setListeners() {
        btnCerrarSesion.setOnClickListener{
            signOff()
        }

        val navigationBottom = binding.bottomNavigation
        navigationBottom.selectedItemId = R.id.option_user
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

                R.id.option_user -> {
                    true
                }
                else -> {
                    super.onOptionsItemSelected(item)
                }
            }
        }
    }

    private fun optionEnProceso() {

        val proceso = Intent(this, EnProcesoActivity::class.java)
        startActivity(proceso)
        finish()
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

    private fun setUserName() {
        val userNameSup = userViewModel.getUserNameSupervisor(Preferences.getSharedPreferenceUserId(sharedPref))
        val userNameEmp = userViewModel.getUserNameEmpleado(Preferences.getSharedPreferenceUserId(sharedPref))
        if(userNameSup != ""){
            txtUserName.text = userNameSup
        }else if (userNameEmp != ""){
            txtUserName.text = userNameEmp

        }else{
            txtUserName.text = getString(R.string.nombre_de_usuario_no_encontrado)
        }
    }

    private fun signOff() {
        sharedPref.edit().clear().apply()
        navigateToLogIn()
        finish()
    }

    private fun navigateToLogIn() {
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }
}