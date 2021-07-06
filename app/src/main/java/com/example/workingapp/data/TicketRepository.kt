package com.example.workingapp.data

import androidx.lifecycle.LiveData

class TicketRepository(private val ticketDao: TicketDao){

    val getAll: LiveData<List<TicketEntity>> = ticketDao.getAll()

    suspend fun addTicket (ticket: TicketEntity){
        ticketDao.addTicket(ticket)
    }
}