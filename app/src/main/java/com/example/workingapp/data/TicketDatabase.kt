package com.example.workingapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Especificamos las entidades que usamos y la versión
@Database(
    entities = [TicketEntity::class],
    version = 1
)
abstract class TicketDatabase : RoomDatabase() {

    abstract fun TicketDao(): TicketDao

    companion object {
        @Volatile
        private var INSTANCE: TicketDatabase? = null

        fun getDatabase(context: Context): TicketDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TicketDatabase::class.java,
                    "Ticket"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}