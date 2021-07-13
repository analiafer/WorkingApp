package com.example.workingapp.model

data class Ticket(
    val id : Long,
    val titulo: String,
    val descripcion: String,
    val autor: String,
    val fechahora : String,
    val estado : String
)
