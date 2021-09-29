package com.example.workingapp.data

class EmpleadoRepository (private val empleadoDAO: EmpleadoDAO) {

    fun getById(id:Int) : EmpleadoEntity? {
        return empleadoDAO.getById(id)
    }
    fun getByEmail(email : String) : EmpleadoEntity? {
        return empleadoDAO.getByEmail(email)
    }
    fun insertEmpleado (user: EmpleadoEntity): Boolean{
        var result=false
        if(getByEmail(user.email) == null){
            empleadoDAO.insert(user)
            result=true
        }
        return result
    }
    fun deletEmpleado (user: EmpleadoEntity) {
            if(getByEmail(user.email) is EmpleadoEntity){
            empleadoDAO.delete(user)
    }
    }
}