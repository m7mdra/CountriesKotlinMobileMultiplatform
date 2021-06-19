package com.m7mdra.countrieskmm.data

import com.m7mdra.countrieskmm.data.database.Database
import com.m7mdra.countrieskmm.data.network.model.Country
import com.m7mdra.countrieskmm.data.network.model.map
import com.m7mdra.countrieskmm.data.network.CountryApi

class CountryRepository(private val countryApi: CountryApi, private val database: Database) {
    suspend fun getAll(refresh: Boolean = false): List<Country> {
        val cachedData = database.getCachedData()
        print("MEGA: ${cachedData.size} found.")
        return if (cachedData.isNotEmpty() && !refresh) {
            print("MEGA: returning ${cachedData.size} record.")

            cachedData.map { it.map() }
        } else {
            print("MEGA: loading new data records.")

            countryApi.getAll().also { list ->
                database.countryQueries.transaction {
                    list.map { it.map() }
                        .forEach(database::insert)
                }
            }
        }
    }
}