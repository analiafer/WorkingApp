package com.example.workingapp

interface TicketsReposotiry {

    fun save (ticket: TicketEntity)
    fun getAll() : List<Ticket>

}