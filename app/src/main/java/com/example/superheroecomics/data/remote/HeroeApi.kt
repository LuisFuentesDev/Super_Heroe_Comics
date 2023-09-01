package com.example.superheroecomics.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroeApi {
    @GET("superheroes")
    suspend fun getData(): Response<List<HeroeData>>

    @GET("superheroes/{id}")
    suspend fun getDetailsData(@Path("id")id:Int): Response<HeroeDetails>

}