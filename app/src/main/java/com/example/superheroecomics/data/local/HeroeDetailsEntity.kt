package com.example.superheroecomics.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes_details_table")
data class HeroeDetailsEntity(
    @PrimaryKey val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    val Año_creacion: Int,
    val color: String,
    val traduccion: Boolean
)
