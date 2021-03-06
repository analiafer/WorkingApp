package com.example.workingapp.data

import android.annotation.SuppressLint
import com.example.workingapp.model.Ticket
import java.text.SimpleDateFormat
import java.util.*

class RoomRepository(private val dao: TicketDao) : TicketsRepository {

    //Todos los comportamientos de las funciones.
    @SuppressLint("SimpleDateFormat")
    override suspend fun save(ticket: TicketEntity) {
        val entity = TicketEntity(
            titulo = ticket.titulo,
            autor = ticket.autor,
            descripcion = ticket.descripcion,
            fechahora = SimpleDateFormat("dd.MM.yyyy HH:mm").format(Date()),
            estado = ticket.estado
        )
        dao.save(entity)
    }

    override suspend fun getAll(): List<Ticket> {
        return dao.getAll().map {
            Ticket(id = it.id,
                   titulo = it.titulo,
                   autor = it.autor,
                   descripcion = it.descripcion,
                   fechahora = it.fechahora,
                   estado = it.estado)
        }
    }

    override suspend fun getByEstado(estado : String): List<Ticket> {
        return dao.getByEstado(estado).map {
            Ticket(id = it.id,
                   titulo = it.titulo,
                    autor = it.autor,
                    descripcion = it.descripcion,
                    fechahora = it.fechahora,
                    estado = it.estado)

        }
    }

    override suspend fun getById(ticketID: Long): TicketEntity {
        return dao.getById(ticketID)
    }

    override suspend fun delete(ticket: TicketEntity) {
        dao.delete(ticket)
    }

    @SuppressLint("SimpleDateFormat")
    override suspend fun update(ticket: TicketEntity) {
        val entity = TicketEntity(
            titulo = ticket.titulo,
            autor = ticket.autor,
            descripcion = ticket.descripcion,
            fechahora = SimpleDateFormat("dd.MM.yyyy HH:mm").format(Date()),
            estado = ticket.estado
        )
        dao.update(entity)
    }


}