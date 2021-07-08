package com.example.workingapp.model

import com.example.workingapp.data.TicketEntity
import com.example.workingapp.model.Ticket

interface TicketsReposotiry {

    fun save (ticket: TicketEntity)
    fun getAll() : List<Ticket>

}