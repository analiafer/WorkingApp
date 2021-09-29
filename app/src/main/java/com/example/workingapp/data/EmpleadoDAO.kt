package com.example.workingapp.data

import androidx.room.*

@Dao
interface EmpleadoDAO {

    @Query("SELECT * FROM empleado WHERE ID = :id")
    fun getById(id:Int):EmpleadoEntity?

    @Query("SELECT * FROM empleado WHERE email = :email")
    fun getByEmail(email:String) : EmpleadoEntity?

    @Insert
    fun insert(empleado: EmpleadoEntity)

    @Delete
    fun delete(empleado: EmpleadoEntity)

    @Update
    fun update(empleado: EmpleadoEntity)
}