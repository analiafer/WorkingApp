package com.example.workingapp.data

import com.example.workingapp.model.Ticket
import com.example.workingapp.model.TicketsReposotiry

class RoomRepository(private val dao : TicketDao) : TicketsReposotiry {

    override fun save(ticket: TicketEntity) {
        val entity = TicketEntity(
            titulo = ticket.titulo,
            autor = ticket.autor,
            descripcion = ticket.descripcion
        )
        dao.save(entity)
    }

    override fun getAll(): List<Ticket> {
        return dao.getAll().map {
            Ticket(id= it.id, titulo = it.titulo, autor = it.autor, descripcion = it.descripcion)
        }
    }
}