package com.example.workingapp.model

import com.example.workingapp.data.TicketEntity
import com.example.workingapp.model.Ticket

interface TicketsRepository {

    //Acá van todas las funciones
    fun save (ticket: TicketEntity)
    fun getAll() : List<Ticket>
    fun getById(ticketID: Long): TicketEntity
    fun delete (ticket: TicketEntity)

}