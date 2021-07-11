package com.example.workingapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TicketDao {

    @Query("SELECT * FROM ticket")
    fun getAll() : List<TicketEntity>

    @Insert
    fun save (ticket : TicketEntity)

}