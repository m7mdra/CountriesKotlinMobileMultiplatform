package com.m7mdra.countrieskmm.android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.m7mdra.countrieskmm.data.CountryRepository
import com.m7mdra.countrieskmm.data.database.Database
import com.m7mdra.countrieskmm.data.database.DatabaseDriverFactory
import com.m7mdra.countrieskmm.data.network.CountryApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CountryViewModel(private val app: Application) : AndroidViewModel(app) {
    private val mainScope = MainScope()
    val state: MutableLiveData<State> = MutableLiveData()
    private val repository by lazy {
        CountryRepository(
            CountryApi(),
            Database(DatabaseDriverFactory(app))
        )
    }

    fun loadCountries() {
        mainScope.launch {


            kotlin.runCatching {
                state.value = LoadingState
                repository.getAll()
            }.onSuccess {
                state.value = Success(it)
            }.onFailure {
                state.value = ErrorState
            }
        }
    }


    override fun onCleared() {
        mainScope.cancel()
        super.onCleared()
    }

    fun filter(i: Int) {
        mainScope.launch {
            kotlin.runCatching {
                state.value = LoadingState
                when (i) {
                    0 -> repository.filterByAlphabetic()
                    1 -> repository.filterByPopulation()
                    3 -> repository.filterByArea()
                    else -> repository.filterByPopulation()
                }
            }.onSuccess {
                state.value = Success(it)
            }.onFailure {
                state.value = ErrorState
            }
        }
    }
}

