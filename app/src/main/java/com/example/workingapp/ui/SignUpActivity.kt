package com.example.workingapp.ui
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workingapp.data.EmpleadoEntity
import com.example.workingapp.data.SupervisorEntity
import com.example.workingapp.databinding.ActivitySignupBinding
import com.example.workingapp.ui.viewModel.SignUpViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SignUpActivity: AppCompatActivity() {

    companion object {

        const val REGEX_EMAIL: String = "^[0-9a-zA-Z._.-]+\\@[0-9a-zA-Z._.-]+\\.[0-9a-zA-Z]+\$"

    }

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signUp: Button
    private lateinit var checkboxSup: CheckBox
    private lateinit var checkboxEmp: CheckBox
    private lateinit var binding: ActivitySignupBinding
    private val signUpViewModel: SignUpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        getViews()
        setListener()
    }


    private fun getViews() {
        name = binding.nameEditTxt
        email = binding.emailEditTxt
        password = binding.passEditTxt
        signUp = binding.signUpBtn
        checkboxSup = binding.CheckboxSupervisor
        checkboxEmp = binding.CheckboxEmpleado
    }

    private fun setListener() {
        signUp.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {

        if (email.text.toString().isNotEmpty()
            && name.text.toString().isNotEmpty()
            && password.text.toString().isNotEmpty()) {

            if (validateEmail(email) && (checkboxEmp.isChecked)){
                if (signUpViewModel.saveNewUserEmpleado(
                        EmpleadoEntity(email = email.text.toString().trim(),
                            password = password.text.toString().trim(),
                            name = name.text.toString().trim())
                    )){
                    Toast.makeText(
                        this@SignUpActivity,
                        "Registración exitosa",
                        Toast.LENGTH_LONG
                    ).show()
                    saveSignUpSharedPreferences(signUpViewModel.getUserIdEmpleado(email.text.toString().trim()))
                    navigateToMainActivity()
                    finish()
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Usuario ya existente con email ${email.text}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }else if (checkboxSup.isChecked){
                if (signUpViewModel.saveNewUserSupervisor(
                        SupervisorEntity(email = email.text.toString().trim(),
                            password = password.text.toString().trim(),
                            name = name.text.toString().trim())
                    )){
                    Toast.makeText(
                        this@SignUpActivity,
                        "Registración exitosa",
                        Toast.LENGTH_LONG
                    ).show()
                    saveSignUpSharedPreferences(signUpViewModel.getUserIdSupervisor(email.text.toString().trim()))
                    navigateToMainActivity()
                    finish()
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Usuario ya existente con email ${email.text}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }else{
                Toast.makeText(
                    this@SignUpActivity,
                    "Formato de email incorrecto o Item no seleccionado",
                    Toast.LENGTH_LONG
                ).show()
            }
        }else {
            Toast.makeText(
                this@SignUpActivity,
                "Por favor rellene todos los campos",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun validateEmail(email: EditText): Boolean {
        return email.text.toString().trim().matches(Regex(pattern = REGEX_EMAIL))
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun saveSignUpSharedPreferences(userId: Int) {
        val sharedPref = this.getSharedPreferences("FILE_PREFERENCES_USER_ID", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt("userId",userId)
        editor.apply()
    }
}

/*if (checkboxSup.isChecked){
                   signUpViewModel.saveNewUserSupervisor(
                       SupervisorEntity(email = email.text.toString().trim(),
                           password = password.text.toString().trim(),
                           name = name.text.toString().trim()))
                   Toast.makeText(
                       this@SignUpActivity,
                       "Registración exitosa",
                       Toast.LENGTH_LONG
                   ).show()
                   saveSignUpSharedPreferences(signUpViewModel.getUserIdSupervisor(email.text.toString().trim()))
                   navigateToMainActivity()
                   finish()
               }else if (checkboxEmp.isChecked){
                   signUpViewModel.saveNewUserEmpleado(
                       EmpleadoEntity(email = email.text.toString().trim(),
                           password = password.text.toString().trim(),
                           name = name.text.toString().trim())
                   )
                   Toast.makeText(
                       this@SignUpActivity,
                       "Registración exitosa",
                       Toast.LENGTH_LONG
                   ).show()
                   saveSignUpSharedPreferences(signUpViewModel.getUserIdEmpleado(email.text.toString().trim()))
                   navigateToMainActivity()
                   finish()
               }else {
                   Toast.makeText(
                       this@SignUpActivity,
                       "Usuario ya existente con email ${email.text}",
                       Toast.LENGTH_LONG
                   ).show()
               }*/