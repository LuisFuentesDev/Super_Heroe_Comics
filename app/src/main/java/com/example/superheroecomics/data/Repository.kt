package com.example.superheroecomics.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.superheroecomics.data.local.HeroeDao
import com.example.superheroecomics.data.local.HeroeEntity
import com.example.superheroecomics.data.remote.HeroeApi

class Repository(private val heroeApi: HeroeApi, private val heroeDao: HeroeDao) {

    fun getHeroeEntity(): LiveData<List<HeroeEntity>> = heroeDao.getHeroe()

    suspend fun getHeroes() {
        try {
            val response = heroeApi.getData()
            if (response.isSuccessful) {
                val phoneList = response.body()
                phoneList?.let {
                    val heroeEntity = it.map { it.transformToEntity() }
                    heroeDao.insertHeroe(heroeEntity)
                }
            } else {
                Log.e("Repository", response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.e("Repository", "Error getting heroes: ${e.message}")
        }
    }
}