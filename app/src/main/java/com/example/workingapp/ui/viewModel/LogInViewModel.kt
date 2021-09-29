package com.example.workingapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.workingapp.data.EmpleadoEntity
import com.example.workingapp.data.EmpleadoRepository
import com.example.workingapp.data.SupervisorEntity
import com.example.workingapp.data.SupervisorRepository

class LogInViewModel(private val userEntityRepository: SupervisorRepository, private val userEntityRepositoryEmpleado: EmpleadoRepository) : ViewModel() {


    fun validateUserSupervisor(email:String, password:String):Boolean{
        var validUser = false
        val user = userEntityRepository.getUserByEmail(email)
        if(user?.password == password){
            validUser = true
        }
        return validUser
    }

    fun validateUserEmpleado(email:String, password:String):Boolean{
        var validUser = false
        val user = userEntityRepositoryEmpleado.getByEmail(email)
        if(user?.password == password){
            validUser = true
        }
        return validUser
    }

    fun getUserIdSupervisor(email:String): Int{
        var userId = 0
        val user = userEntityRepository.getUserByEmail(email)
        if(user is SupervisorEntity){
            userId = user.id
        }
        return userId
    }

    fun getUserIdEmpleado(email:String): Int{
        var userId = 0
        val user = userEntityRepositoryEmpleado.getByEmail(email)
        if(user is EmpleadoEntity){
            userId = user.id
        }
        return userId
    }
}