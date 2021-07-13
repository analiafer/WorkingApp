package com.example.workingapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.data.TicketsRepository
import kotlinx.coroutines.launch

class EditActivityViewModel (private val repository: TicketsRepository) : ViewModel(){
    val ticket = MutableLiveData<TicketEntity>()

    fun updateTicket(ticket: TicketEntity){
        viewModelScope.launch {
        repository.update(ticket)
        }

    }

    fun getById(ID: Long){
        ticket.value = repository.getById(ID)

    }

}