package com.example.workingapp.data

import androidx.room.*

@Dao
interface SupervisorDAO{

@Query("SELECT * FROM supervisor WHERE supervisor.id = :id")
fun getById(id:Int):SupervisorEntity?

@Query("SELECT * FROM supervisor WHERE supervisor.email like :email")
fun getUserByEmail(email:String) : SupervisorEntity?

@Insert
fun insert(supervisor: SupervisorEntity)

@Delete
fun delete(supervisor: SupervisorEntity)

@Update
fun update(supervisor: SupervisorEntity)
}