package com.example.workingapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


//Contiene los metodos para acceder a la base de datos.
@Dao
interface TicketDao {

    //Usamos INSERT para insertar elementos.
    //Agrego un nuevo elemento a la lista.
    @Insert
    suspend fun addTicket (ticket: TicketEntity)

    //@Delete (Borrar) @Update (Actualizar)

    //Hago una Query de la base de datos, trayendo todos los datos de Ticket
    //Termino devolviendo una lista de los mismos.
    @Query("SELECT * FROM Ticket")
    fun getAll(): LiveData<List<TicketEntity>>
}