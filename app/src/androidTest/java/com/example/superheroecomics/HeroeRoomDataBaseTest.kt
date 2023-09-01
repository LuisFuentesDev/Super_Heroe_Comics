package com.example.superheroecomics

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.superheroecomics.data.local.HeroeDao
import com.example.superheroecomics.data.local.HeroeDataBase
import com.example.superheroecomics.data.local.HeroeEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class HeroeRoomDataBaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var heroeDao: HeroeDao
    private lateinit var heroeDataBase: HeroeDataBase

    @Before
    fun setUp() {
        //Fake
        val context = ApplicationProvider.getApplicationContext<Context>()
        heroeDataBase =
            Room.inMemoryDatabaseBuilder(context, HeroeDataBase::class.java).build()
        heroeDao = heroeDataBase.getHeroeDao()
    }

    @After
    fun tearDown() {
        heroeDataBase.close()
    }

    @Test
    fun insertPhones_empty() = runBlocking {
        // Given
        val heroeList = listOf<HeroeEntity>()

        // When
        heroeDao.insertHeroe(heroeList)

        // Then A
        val it = heroeDao.getHeroe().getOrAwaitValue()

        assertThat(it).isNotNull()
        assertThat(it).isEmpty()


        // Then B
        heroeDao.getHeroe().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertPhones_happyCase_1element() = runBlocking {
        // Given
        val heroeList = listOf(HeroeEntity(1, "hulk", "Tierra", "www.image.com", "fuerza", 1956))

        // When
        heroeDao.insertHeroe(heroeList)

        // Then
        heroeDao.getHeroe().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertHeroe_happyCase_3elements() = runBlocking {
        // Given
        val heroeList =
            listOf(
                HeroeEntity(1, "batman", "Tierra", "www.heroe1.com", "fuerza", 1956),
                HeroeEntity(2, "flash", "Tierra", "www.heroe2.com", "velocidad", 1990),
                HeroeEntity(3, "hulk", "Tierra", "www.heroe3.com", "super Fuerza", 1885)
            )

        // When
        heroeDao.insertHeroe(heroeList)

        // Then
        heroeDao.getHeroe().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }


    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(value: T) {
                data = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)

        try {
            afterObserve.invoke()

            // Don't wait indefinitely if the LiveData is not set.
            if (!latch.await(time, timeUnit)) {
                throw TimeoutException("LiveData value was never set.")
            }

        } finally {
            this.removeObserver(observer)
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}

