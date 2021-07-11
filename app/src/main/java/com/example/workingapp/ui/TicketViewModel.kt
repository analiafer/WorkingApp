package com.example.workingapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.model.Ticket
import com.example.workingapp.model.TicketsRepository

//Algunas cosas deben estar en otra ViewModel pero todavia no conseguimos que funcione, sin embargo en está si funciona.
class TicketViewModel (private val repository: TicketsRepository) : ViewModel(){

    val ticketLiveData = MutableLiveData<List<Ticket>>()
    val ticket = MutableLiveData<TicketEntity>()

    fun delete(ticket: TicketEntity){
        repository.delete(ticket)
        ticketLiveData.value = repository.getAll()
    }

    fun getById(ID: Long){
        ticket.value = repository.getById(ID)
    }

   /* init{
        viewModelScope.launch {
            val allNotes = repository.getAll()
            ticketLiveData.value = allNotes;
        }
    }*/

    fun getAll(){
        ticketLiveData.value = repository.getAll()
    }


    fun saveTicket(titulo: String, autor : String, contenido : String){
        repository.save(TicketEntity(titulo = titulo, autor = autor, descripcion = contenido))
        ticketLiveData.value = repository.getAll()
    }
    fun updateTicket(ticket: TicketEntity){
        repository.update(ticket)
        ticketLiveData.value = repository.getAll()
    }



}