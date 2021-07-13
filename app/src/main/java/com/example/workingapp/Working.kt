package com.example.workingapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.workingapp.data.*
import com.example.workingapp.data.service.DataSource
import com.example.workingapp.ui.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Working : Application() {

    private val appModule = module{

        single{ DataSource() }
        single <RepositorioClima> { ClimaImpl(get()) }
        viewModel{ ClimaViewModel(get()) }

        single<TicketDao> {dataBase(get()).ticketDao()}
        single<TicketsRepository>{RoomRepository(get())}
        viewModel { TicketViewModel(get()) }
        viewModel { AddTicketViewModel(get()) }
        viewModel { ViewTicketViewModel(get()) }
        viewModel { EditActivityViewModel(get()) }


    }

    override fun onCreate(){

        super.onCreate()

        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@Working)
            modules(appModule)
        }
    }

     fun dataBase( context : Context) : AppDatabase {
         return Room.databaseBuilder(
             context,
             AppDatabase::class.java,
             "workingApplication"
         )
             .allowMainThreadQueries()
             .build()
    }

}

