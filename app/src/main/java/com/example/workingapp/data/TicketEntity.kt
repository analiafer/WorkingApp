package com.example.workingapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ticket")
data class TicketEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Long = 0,

    @ColumnInfo(name = "titulo")
    val titulo: String,

    @ColumnInfo(name = "autor")
    val autor : String,

    @ColumnInfo(name = "descripcion")
    val descripcion : String,

    @ColumnInfo(name = "fechahora")
    val fechahora : String,

    @ColumnInfo(name = "estado")
    val estado : String
)

