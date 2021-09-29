package com.example.workingapp.data

class SupervisorRepository (private val supervisorDAO: SupervisorDAO){

    fun getUserByEmail(email:String): SupervisorEntity? {
        return supervisorDAO.getUserByEmail(email = email)
    }
    fun getById(id:Int): SupervisorEntity? {
        return supervisorDAO.getById(id = id)
    }
    fun insertNewUser(user:SupervisorEntity):Boolean{
        var result=false
        if(getUserByEmail(user.email) == null){
            supervisorDAO.insert(user)
            result=true
        }
        return result
    }
    fun deletEmpleado (user: SupervisorEntity) {
        if(getUserByEmail(user.email) is SupervisorEntity){
            supervisorDAO.delete(user)
        }
    }
}