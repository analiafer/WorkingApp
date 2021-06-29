package com.example.workingapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [TicketEntity::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ticketDao() : TicketDao

}