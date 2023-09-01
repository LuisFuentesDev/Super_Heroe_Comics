package com.example.superheroecomics.vista

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroecomics.data.Repository
import com.example.superheroecomics.data.local.HeroeDataBase
import com.example.superheroecomics.data.remote.HeroeRetrofit
import kotlinx.coroutines.launch

class HeroeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    fun heroeLiveData() = repository.getHeroeEntity()

    fun heroeDetailsLiveData(id: Int) = repository.getHeroeDetailsEntity(id)

    init {
        val heroeApi = HeroeRetrofit.getHeroeRetrofit()
        val heroeDataBase = HeroeDataBase.getDataBase(application).getHeroeDao()
        repository = Repository(heroeApi, heroeDataBase)

    }

    fun getAllHeroes() = viewModelScope.launch {
        repository.getHeroes()

    }
    fun getDetailsHero(id: Int) = viewModelScope.launch {
        repository.getHeroeDetails(id)
    }
}