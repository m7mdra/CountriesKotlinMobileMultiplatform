package com.m7mdra.countrieskmm.data

import com.m7mdra.countrieskmm.data.database.Database
import com.m7mdra.countrieskmm.data.network.model.Country
import com.m7mdra.countrieskmm.data.network.model.map
import com.m7mdra.countrieskmm.data.network.CountryApi
import com.m7mdra.countrieskmm.logger.log

class CountryRepository(private val countryApi: CountryApi = CountryApi(), private val database: Database) {
    suspend fun getAll(refresh: Boolean = false): List<Country> {
        val cachedData = database.getCachedData()
        print("MEGA: ${cachedData.size} found.")
        return if (cachedData.isNotEmpty() && !refresh) {
            log("MEGA: returning ${cachedData.size} record.")

            cachedData.map { it.map() }
        } else {
            log("MEGA: loading new data records.")

            countryApi.getAll().also { list ->
                database.countryQueries.transaction {
                    list.map { it.map() }
                        .forEach(database::insert)
                }
            }
        }
    }
}