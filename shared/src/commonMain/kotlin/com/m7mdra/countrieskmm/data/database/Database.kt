package com.m7mdra.countrieskmm.data.database

import com.example.AppDatabase
import com.example.Country

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())


    fun getCachedData(): List<Country> {
        //TODO Map to network model
        return database.countryQueries.getCountries().executeAsList()
    }

}