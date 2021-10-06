package com.example.workingapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.workingapp.data.*
import com.example.workingapp.data.service.DataSource
import com.example.workingapp.ui.UserActivity
import com.example.workingapp.ui.viewModel.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class Working : Application() {

    private val appModule = module{

        single{ DataSource() }
        single<RepositorioClima> { ClimaImpl(get()) }
        viewModel{ ClimaViewModel(get()) }
        single<EmpleadoDAO> {dataBase(get()).EmpleadoDAO()}
        single<SupervisorDAO> {dataBase(get()).SupervisorDAO()}
        single<TicketDao> {dataBase(get()).ticketDao()}
        single<SupervisorRepository>{ SupervisorRepository(get()) }
        single<EmpleadoRepository>{ EmpleadoRepository(get()) }
        single<TicketsRepository>{RoomRepository(get())}
        viewModel { TicketViewModel(get()) }
        viewModel { NewTicketViewModel(get()) }
        viewModel { ViewTicketViewModel(get()) }
        viewModel { EditActivityViewModel(get()) }
        viewModel { DoneViewModel(get()) }
        viewModel { CancelViewModel(get())}
        viewModel { EnProcesoViewModel(get()) }
        viewModel { LogInViewModel(get(), get())}
        viewModel { SignUpViewModel(get(), get())}
        viewModel { UserViewModel(get(), get()) }
    }

    override fun onCreate(){

        super.onCreate()

        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@Working)
            modules(appModule)
        }
    }
        fun dataBase(context: Context) : AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "appWorking"
            )
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_4)
                .build()
        }

        val MIGRATION_1_4 = object : Migration(1, 4){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `supervisor` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `email` TEXT NOT NULL, `password` TEXT NOT NULL, `name` TEXT NOT NULL)")
            database.execSQL("CREATE TABLE IF NOT EXISTS `empleado` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `email` TEXT NOT NULL, `password` TEXT NOT NULL, `name` TEXT NOT NULL)")
        }
    }
}

