package com.m7mdra.countrieskmm.data

import com.m7mdra.countrieskmm.data.database.Database
import com.m7mdra.countrieskmm.data.network.model.Country
import com.m7mdra.countrieskmm.data.network.model.mapTo
import com.m7mdra.myapplication.network.CountryApi

class CountryRepository(private val countryApi: CountryApi, private val database: Database) {
    suspend fun getAll(refresh: Boolean = false) :List<Country> {
        val cachedData = database.getCachedData()
        return if(cachedData.isNotEmpty() && !refresh){
             cachedData.map { it.mapTo() }
        }else{
            countryApi.getAll().also {

            }
        }
    }
}