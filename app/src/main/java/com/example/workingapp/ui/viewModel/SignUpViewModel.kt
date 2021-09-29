package com.example.workingapp.ui.viewModel


import androidx.lifecycle.ViewModel
import com.example.workingapp.data.EmpleadoEntity
import com.example.workingapp.data.EmpleadoRepository
import com.example.workingapp.data.SupervisorEntity
import com.example.workingapp.data.SupervisorRepository

class SignUpViewModel (private var userEntitySupervisor: SupervisorRepository, private var userEntityEmpleado: EmpleadoRepository) : ViewModel() {

    fun saveNewUserSupervisor(newUser: SupervisorEntity):Boolean{
        return userEntitySupervisor.insertNewUser(newUser)
    }

    fun saveNewUserEmpleado(newUser: EmpleadoEntity):Boolean{
        return userEntityEmpleado.insertEmpleado(newUser)
    }

    fun getUserIdSupervisor(email:String): Int{
        var userId = 0
        var user = userEntitySupervisor.getUserByEmail(email)
        if(user is SupervisorEntity){
            userId = user.id
        }
        return userId
    }

    fun getUserIdEmpleado(email:String): Int{
        var userId = 0
        var user = userEntityEmpleado.getByEmail(email)
        if(user is EmpleadoEntity){
            userId = user.id
        }
        return userId
    }
}