package com.example.workingapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workingapp.data.TicketsRepository
import com.example.workingapp.model.Ticket
import kotlinx.coroutines.launch

class EnProcesoViewModel (private val repository: TicketsRepository) : ViewModel() {

    val procesoLiveData = MutableLiveData<List<Ticket>>()
    private val enProceso : String = "en proceso"

    fun getAllEnProceso(){
        viewModelScope.launch {
            procesoLiveData.value = repository.getByEstado(enProceso)
        }
    }

}

