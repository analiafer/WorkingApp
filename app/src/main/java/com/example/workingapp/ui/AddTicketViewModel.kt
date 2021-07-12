package com.example.workingapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.model.Ticket
import com.example.workingapp.data.TicketsRepository
import kotlinx.coroutines.launch

class AddTicketViewModel  (private val repository: TicketsRepository) : ViewModel(){

    val ticketLiveData = MutableLiveData<List<Ticket>>()

    fun getAll(){
        viewModelScope.launch {
            ticketLiveData.value = repository.getAll()
        }

    }

    fun saveTicket(titulo: String, autor : String, contenido : String){
        viewModelScope.launch {
            repository.save(TicketEntity(titulo = titulo, autor = autor, descripcion = contenido))
            ticketLiveData.value = repository.getAll()
        }

    }

}