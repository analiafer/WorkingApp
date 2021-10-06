package com.example.workingapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.workingapp.data.EmpleadoEntity
import com.example.workingapp.data.EmpleadoRepository
import com.example.workingapp.data.SupervisorEntity
import com.example.workingapp.data.SupervisorRepository

class UserViewModel(private val userSupervisor: SupervisorRepository, private val userEmpleado: EmpleadoRepository): ViewModel() {

    fun getUserNameSupervisor(userId:Int):String{
        var name = ""
        val user = userSupervisor.getById(userId)
        if (user is SupervisorEntity){
            name = user.name
        }
        return name
    }

    fun getUserNameEmpleado(userId: Int):String{
        var name = ""
        val user = userEmpleado.getById(userId)
        if (user is EmpleadoEntity){
            name = user.name
        }
        return name
    }
}