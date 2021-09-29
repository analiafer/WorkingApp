package com.example.workingapp.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

    @Entity (tableName = "empleado")
    data class EmpleadoEntity (
        @NonNull
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id : Int = 0,

        @NonNull
        @ColumnInfo(name = "email")
        val email : String,

        @NonNull
        @ColumnInfo(name = "password")
        val password : String,

        @NonNull
        @ColumnInfo(name = "name")
        var name : String
    )
