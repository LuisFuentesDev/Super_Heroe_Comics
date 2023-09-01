package com.example.superheroecomics.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeroeRetrofit {
    companion object {
        private const val URL_BASE = "https://y-mariocanedo.vercel.app/"

        fun getHeroeRetrofit(): HeroeApi {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(HeroeApi::class.java)
        }
    }
}