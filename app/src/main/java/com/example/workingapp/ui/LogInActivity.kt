package com.example.workingapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workingapp.databinding.ActivityLoginBinding
import com.example.workingapp.ui.viewModel.LogInViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LogInActivity : AppCompatActivity() {
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var btnLogIn : Button
    private lateinit var btnSignUp : Button
    private lateinit var binding : ActivityLoginBinding
    private val logInViewModel: LogInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        getViews()
        setListeners()
    }
    
    private fun getViews() {
        email = binding.emailEditText
        password = binding.passwordEditText
        btnLogIn = binding.logInButton
        btnSignUp = binding.signUpButton
    }

    private fun setListeners() {
        btnLogIn.setOnClickListener{
            validateFields()
        }
        btnSignUp.setOnClickListener{
            navigateToSignUpActivity()
        }
    }

    private fun navigateToSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun validateFields() {
        if(email.text.toString().isNotEmpty()
            && password.text.toString().isNotEmpty()){
            if(logInViewModel.validateUserSupervisor(
                    email = email.text.toString().trim(),
                    password = password.text.toString().trim()
                )){
                saveLogInSharedPreferences(logInViewModel.getUserIdSupervisor(email.text.toString().trim()))
                navigateToMainActivity()
                finish()
            }else if(logInViewModel.validateUserEmpleado(
                    email = email.text.toString().trim(),
                    password = password.text.toString().trim()
                )){
                saveLogInSharedPreferences(logInViewModel.getUserIdEmpleado(email.text.toString().trim()))
                navigateToMainActivity()
                finish()
            }else{
                Toast.makeText(
                    this@LogInActivity,
                    "Usuario incorrecto, registrese",
                    Toast.LENGTH_LONG
                ).show()
            }
        }else{
            Toast.makeText(
                this@LogInActivity,
                "Por favor rellene todos los campos",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun saveLogInSharedPreferences(userId: Int) {
        val sharedPref = this.getSharedPreferences("FILE_PREFERENCES_USER_ID", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt("userId",userId)
        editor.apply()
    }
}