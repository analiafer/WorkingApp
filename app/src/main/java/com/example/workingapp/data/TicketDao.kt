package com.example.workingapp.data

import androidx.room.*
import com.example.workingapp.model.Ticket

@Dao
interface TicketDao {

    //Acá van todas las funciones y sus comportamientos en la base de datos (Query, update, remove, insert...)
    @Query("SELECT * FROM ticket")
    fun getAll() : List<TicketEntity>

    @Query("SELECT * FROM ticket WHERE ID = :ticketID")
    fun getById(ticketID: Long): TicketEntity

    @Insert
    fun save (ticket : TicketEntity)

    @Delete
    fun delete (ticket: TicketEntity)

    @Update
    fun update(ticket: TicketEntity)
}