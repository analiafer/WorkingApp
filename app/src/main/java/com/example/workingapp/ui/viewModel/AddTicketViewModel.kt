package com.example.workingapp.ui.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workingapp.data.TicketEntity
import com.example.workingapp.model.Ticket
import com.example.workingapp.data.TicketsRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddTicketViewModel  (private val repository: TicketsRepository) : ViewModel(){

    val ticketLiveData = MutableLiveData<List<Ticket>>()

    fun getAll(){
        viewModelScope.launch {
            ticketLiveData.value = repository.getAll()
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun saveTicket(titulo: String, autor : String, contenido : String){
        viewModelScope.launch {
            repository.save(TicketEntity(titulo = titulo,
                                         autor = autor,
                                         descripcion = contenido,
                                         fechahora = SimpleDateFormat("dd.MM.yyyy HH:mm").format(Date())))
            ticketLiveData.value = repository.getAll()
        }

    }

}