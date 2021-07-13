package com.example.workingapp.ui

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.model.Ticket
import com.example.workingapp.data.TicketsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class TicketViewModel (private val repository: TicketsRepository) : ViewModel(){

    val ticketLiveData = MutableLiveData<List<Ticket>>()
    val ticket = MutableLiveData<TicketEntity>()

    @SuppressLint("SimpleDateFormat")
    fun saveTicket(titulo: String, autor : String, contenido : String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.save(TicketEntity(titulo = titulo,
                                         autor = autor,
                                         descripcion = contenido,
                                         fechahora = SimpleDateFormat("dd.MM.yyyy HH:mm").format(Date()),
                                         estado = " "
                                         )
            )
            ticketLiveData.value = repository.getAll()
        }
    }

    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO){
        repository.update(ticket)}
    }



}