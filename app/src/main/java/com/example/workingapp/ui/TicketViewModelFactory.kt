package com.example.workingapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.workingapp.data.AppDatabase
import com.example.workingapp.data.RoomRepository


/*@Suppress("UNCHECKED_CAST")
class TicketViewModelFactory (private val applicationContext : Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) : T {

        return TicketViewModel(
            RoomRepository(AppDatabase.getDatabase(applicationContext).ticketDao())
        ) as T

    }

}*/