package com.example.workingapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TicketViewModel(application: Application) : AndroidViewModel(application) {

    private val getAll: LiveData<List<TicketEntity>>
    private val repository: TicketRepository

    init {
        val ticketDao = TicketDatabase.getDatabase(application).TicketDao()
        repository = TicketRepository(ticketDao)
        getAll = repository.getAll
    }

    fun addTicket(ticket: TicketEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTicket(ticket)
        }
    }


}