package com.example.superheroecomics.data

import com.example.superheroecomics.data.remote.HeroeData
import com.example.superheroecomics.data.remote.HeroeDataDetails
import org.junit.Test
import org.junit.Assert.assertEquals

class MappertTest {

    @Test
    fun transformToEntity() {
        //Given
        val heroes = HeroeData(1, "superman", "kripton", "wwww.image.cl", "superfuerza", 1960)

        //When
        val resultadoTransformacionHeroe = heroes.transformToEntity()

        //Then
        assertEquals(heroes.id, resultadoTransformacionHeroe.id)
        assertEquals(heroes.nombre, resultadoTransformacionHeroe.nombre)
        assertEquals(heroes.origen, resultadoTransformacionHeroe.origen)
        assertEquals(heroes.imagenLink, resultadoTransformacionHeroe.imagenLink)
        assertEquals(heroes.poder, resultadoTransformacionHeroe.poder)
        assertEquals(heroes.Año_creacion, resultadoTransformacionHeroe.Año_creacion)
    }

    @Test
    fun transformToDetailEntity() {
        //Given
        val heroeDetails =
            HeroeDataDetails(
                1,
                "batman",
                "ciudad gotica",
                "wwww.image.cl",
                "negro",
                1948,
                "negro",
                true
            )

        //When
        val resultadoTransformacionCellPhoneDetail = heroeDetails.transformToDetailEntity()

        //Then
        assertEquals(heroeDetails.id, resultadoTransformacionCellPhoneDetail.id)
        assertEquals(heroeDetails.nombre, resultadoTransformacionCellPhoneDetail.nombre)
        assertEquals(heroeDetails.origen, resultadoTransformacionCellPhoneDetail.origen)
        assertEquals(heroeDetails.imagenLink, resultadoTransformacionCellPhoneDetail.imagenLink)
        assertEquals(heroeDetails.poder, resultadoTransformacionCellPhoneDetail.poder)
        assertEquals(heroeDetails.año_creacion, resultadoTransformacionCellPhoneDetail.año_creacion)
        assertEquals(heroeDetails.color, resultadoTransformacionCellPhoneDetail.color)
        assertEquals(heroeDetails.traduccion, resultadoTransformacionCellPhoneDetail.traduccion)
    }
}
