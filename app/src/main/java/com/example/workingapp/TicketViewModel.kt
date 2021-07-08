package com.example.workingapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TicketViewModel (private val repository: TicketsReposotiry) : ViewModel(){

    val ticketLiveData = MutableLiveData<List<Ticket>>()

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




}