package com.example.workingapp.data

import com.example.workingapp.data.TicketEntity
import com.example.workingapp.model.Ticket

interface TicketsRepository {

    //Acá van todas las funciones
    suspend fun save (ticket: TicketEntity)
    suspend fun getAll() : List<Ticket>
    fun getById(ticketID: Long): TicketEntity
    fun delete (ticket: TicketEntity)
    suspend fun update (ticket: TicketEntity)
}