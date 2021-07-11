package com.example.workingapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.model.TicketsRepository

//Esta clase es el ViewModel del ViewTicket pero por ahora no funciona y no sabemos la razón, así que usamos la general.
class ViewTicketViewModel (private val repository: TicketsRepository) : ViewModel(){

    val ticket = MutableLiveData<TicketEntity>()

    fun getById(ID: Long){
        ticket.value = repository.getById(ID)
    }
}


