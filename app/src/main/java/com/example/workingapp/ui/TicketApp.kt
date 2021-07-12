package com.example.workingapp.ui

import android.app.Application
import com.example.workingapp.data.RoomRepository
import com.example.workingapp.data.TicketDao
import com.example.workingapp.data.TicketDao_Impl
import com.example.workingapp.model.TicketsRepository
import com.example.workingapp.ui.viewModel.TicketViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TicketApp : Application() {

    private val appModule = module {
        single<TicketsRepository> { RoomRepository(get()) }
        viewModel { TicketViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TicketApp)
            modules(appModule)
        }
    }
}