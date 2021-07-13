package com.example.workingapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workingapp.data.TicketsRepository
import com.example.workingapp.model.Ticket
import kotlinx.coroutines.launch

class DoneViewModel(private val repository: TicketsRepository) : ViewModel() {

    val doneLiveData = MutableLiveData<List<Ticket>>()
    private val done : String = "realizado"

    fun getAllDone(){
        viewModelScope.launch {
            doneLiveData.value = repository.getByEstado(done)
        }
    }


}