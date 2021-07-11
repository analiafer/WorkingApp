package com.example.workingapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [TicketEntity::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ticketDao() : TicketDao

    companion object {

        private var db: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {

            if (db == null) {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "working"
                )
                    .allowMainThreadQueries()
                    .build()
            }

            return db!!

        }

    }

}