package com.example.workingapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.model.Ticket
import com.example.workingapp.data.TicketsRepository
import kotlinx.coroutines.launch

//Algunas cosas deben estar en otra ViewModel pero todavia no conseguimos que funcione, sin embargo en está si funciona.
class TicketViewModel (private val repository: TicketsRepository) : ViewModel(){

    val ticketLiveData = MutableLiveData<List<Ticket>>()
    val ticket = MutableLiveData<TicketEntity>()

    fun saveTicket(titulo: String, autor : String, contenido : String){
        viewModelScope.launch {
            repository.save(TicketEntity(titulo = titulo, autor = autor, descripcion = contenido))
            ticketLiveData.value = repository.getAll()
        }
    }

    fun getAll(){
        viewModelScope.launch {
            ticketLiveData.value = repository.getAll()
        }
    }

    fun getById(ID: Long){
        ticket.value = repository.getById(ID)
    }

    fun delete(ticket: TicketEntity){
        repository.delete(ticket)

    }

    fun updateTicket(ticket: TicketEntity){
        repository.update(ticket)

    }



}