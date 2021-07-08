package com.example.workingapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddTicketViewModel  (private val repository: TicketsReposotiry) : ViewModel(){

    val ticketLiveData = MutableLiveData<List<Ticket>>()



    fun getAll(){
        ticketLiveData.value = repository.getAll()
    }

    fun saveTicket(titulo: String, autor : String, contenido : String){
        repository.save(TicketEntity(titulo = titulo, autor = autor, descripcion = contenido))
        ticketLiveData.value = repository.getAll()
    }

}