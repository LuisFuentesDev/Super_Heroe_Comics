package com.example.superheroecomics.data

import com.example.superheroecomics.data.local.HeroeDetailsEntity
import com.example.superheroecomics.data.local.HeroeEntity
import com.example.superheroecomics.data.remote.HeroeData
import com.example.superheroecomics.data.remote.HeroeDataDetails

fun HeroeData.transformToEntity(): HeroeEntity =
    HeroeEntity(this.id, this.nombre, this.origen, this.imagenLink, this.poder, this.Año_creacion)

fun HeroeDataDetails.transformToDetailEntity(): HeroeDetailsEntity =
    HeroeDetailsEntity(this.id, this.nombre, this.origen, this.imagenLink, this.poder, this.año_creacion, this.poder, this.traduccion)
