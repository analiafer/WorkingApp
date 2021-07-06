package com.example.workingapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//Creamos la entidad, como se va a componer la tabla
//tableName hace referencia al nombre de la tabla.
@Entity(tableName = "Ticket")
data class TicketEntity(
    //Creamos una ID y le agregamos el autogenerate, para autogenerar los numeros sucesivamente.
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val autor: String
    //falta fecha
)