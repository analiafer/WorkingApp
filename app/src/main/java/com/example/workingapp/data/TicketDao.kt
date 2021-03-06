package com.example.workingapp.data

import androidx.room.*
import com.example.workingapp.model.Ticket

@Dao
interface TicketDao {

    //Acá van todas las funciones y sus comportamientos en la base de datos (Query, update, remove, insert...)

    @Query("SELECT * FROM ticket ")
    suspend fun getAll() : List<TicketEntity>

    @Query("SELECT * FROM ticket WHERE ID = :ticketID")
    suspend fun getById(ticketID: Long): TicketEntity

    @Query("SELECT * FROM ticket WHERE estado = :estado")
    suspend fun getByEstado(estado: String) : List<TicketEntity>

    @Insert
    suspend fun save(ticket: TicketEntity)

    @Delete
    suspend fun delete(ticket: TicketEntity)

    @Update
    suspend fun update(ticket: TicketEntity)
}
