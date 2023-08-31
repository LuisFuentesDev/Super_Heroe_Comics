package com.example.superheroecomics.data

import com.example.superheroecomics.data.local.HeroeEntity
import com.example.superheroecomics.data.remote.HeroeData

fun HeroeData.transformToEntity(): HeroeEntity =
    HeroeEntity(this.id, this.nombre, this.origen, this.imagenLink, this.poder, this.Año_creacion)