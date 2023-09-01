package com.example.superheroecomics.data.remote

data class HeroeDataDetails(
    val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    val año_creacion: Int,
    val color: String,
    val traduccion: Boolean
)