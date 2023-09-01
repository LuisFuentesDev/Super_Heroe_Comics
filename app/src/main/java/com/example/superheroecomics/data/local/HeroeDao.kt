package com.example.superheroecomics.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroeDao {
    //Lista
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroe(heroeEntity: List<HeroeEntity>)

    @Query("SELECT * FROM heroes_table order by id asc")
    fun getHeroe(): LiveData<List<HeroeEntity>>
}