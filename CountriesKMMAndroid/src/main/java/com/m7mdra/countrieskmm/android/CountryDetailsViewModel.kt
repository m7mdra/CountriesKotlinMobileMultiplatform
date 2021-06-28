package com.m7mdra.countrieskmm.android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.m7mdra.countrieskmm.data.CountryRepository
import com.m7mdra.countrieskmm.data.database.Database
import com.m7mdra.countrieskmm.data.database.DatabaseDriverFactory
import com.m7mdra.countrieskmm.data.network.CountryApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CountryDetailsViewModel(private val app: Application) : AndroidViewModel(app) {
    private val mainScope = MainScope()
    val state: MutableLiveData<State> = MutableLiveData()
    private val repository by lazy {
        CountryRepository(
            CountryApi(),
            Database(DatabaseDriverFactory(app))
        )
    }
    fun getBorders(ids:List<String>){
        mainScope.launch {
            kotlin.runCatching {
                state.value = LoadingState
                repository.getBorderingCountries(ids)
            }.onSuccess {
                state.value = Success(it)
            }.onFailure {
                state.value = ErrorState
            }
        }
    }
}