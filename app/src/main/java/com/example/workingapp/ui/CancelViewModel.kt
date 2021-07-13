package com.example.workingapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workingapp.data.TicketsRepository
import com.example.workingapp.model.Ticket
import kotlinx.coroutines.launch

class CancelViewModel (private val repository: TicketsRepository) : ViewModel() {

    val cancelLiveData = MutableLiveData<List<Ticket>>()
    private val cancel: String = "cancelado"

    fun getAllCancel(){
        viewModelScope.launch {
            cancelLiveData.value = repository.getByEstado(cancel)
        }
    }
}