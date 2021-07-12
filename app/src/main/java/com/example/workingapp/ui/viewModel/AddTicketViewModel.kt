package com.example.workingapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.model.Ticket
import com.example.workingapp.model.TicketsRepository

class AddTicketViewModel  (private val repository: TicketsRepository) : ViewModel(){

    val ticketLiveData = MutableLiveData<List<Ticket>>()



    fun getAll(){
        ticketLiveData.value = repository.getAll()
    }

    fun saveTicket(titulo: String, autor : String, contenido : String){
        repository.save(TicketEntity(titulo = titulo, autor = autor, descripcion = contenido))
        ticketLiveData.value = repository.getAll()
    }

}