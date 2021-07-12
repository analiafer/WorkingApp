package com.example.workingapp

import android.app.Application
import com.example.workingapp.data.ClimaImpl
import com.example.workingapp.data.RepositorioClima
import com.example.workingapp.data.service.DataSource
import com.example.workingapp.ui.ClimaViewModel
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

    }

    override fun onCreate(){

        super.onCreate()

        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@Working)
            modules(appModule)
        }
    }

}