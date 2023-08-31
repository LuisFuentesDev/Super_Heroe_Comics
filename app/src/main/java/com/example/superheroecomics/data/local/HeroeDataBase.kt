package com.example.superheroecomics.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HeroeEntity::class], version = 1)
abstract class HeroeDataBase : RoomDatabase() {

    abstract fun getHeroeDao(): HeroeDao

    companion object {
        @Volatile
        private var INSTANCE: HeroeDataBase? = null

        fun getDataBase(context: Context): HeroeDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HeroeDataBase::class.java,
                    "phones_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}


}