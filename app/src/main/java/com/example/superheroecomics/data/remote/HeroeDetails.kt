package com.example.superheroecomics.data.remote

data class HeroeDetails(
    val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    val Año_creacion: Int,
    val color: String,
    val traduccion: String
)