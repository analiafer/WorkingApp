package com.example.workingapp.data

//import android.content.Context
import android.content.Context
import androidx.room.Database
import androidx.room.Room
//import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 4,
    entities = [TicketEntity::class, SupervisorEntity::class, EmpleadoEntity::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ticketDao(): TicketDao
    abstract fun SupervisorDAO(): SupervisorDAO
    abstract fun EmpleadoDAO(): EmpleadoDAO

}

