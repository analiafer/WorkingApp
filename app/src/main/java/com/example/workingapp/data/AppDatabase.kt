package com.example.workingapp.data

//import android.content.Context
import android.content.Context
import androidx.room.Database
import androidx.room.Room
//import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [TicketEntity::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ticketDao() : TicketDao

    /*companion object {

        private var db: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {

            if (db == null) {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "workingApplication"
                )
                    .allowMainThreadQueries()
                    .build()
            }

            return db!!

        }

    }*/

}