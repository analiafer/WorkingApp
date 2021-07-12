package com.example.workingapp.data

import com.example.workingapp.model.Ticket

class RoomRepository(private val dao: TicketDao) : TicketsRepository {

    //Todos los comportamientos de las funciones.
    override suspend fun save(ticket: TicketEntity) {
        val entity = TicketEntity(
            titulo = ticket.titulo,
            autor = ticket.autor,
            descripcion = ticket.descripcion
        )
        dao.save(entity)
    }

    override suspend fun getAll(): List<Ticket> {
        return dao.getAll().map {
            Ticket(id = it.id, titulo = it.titulo, autor = it.autor, descripcion = it.descripcion)
        }
    }

    override fun getById(ticketID: Long): TicketEntity {
        return dao.getById(ticketID)

    }

    override fun delete(ticket: TicketEntity) {
        dao.delete(ticket)
    }

    override fun update(ticket: TicketEntity) {
        val entity = TicketEntity(
            titulo = ticket.titulo,
            autor = ticket.autor,
            descripcion = ticket.descripcion
        )
        dao.update(entity)

    }
}