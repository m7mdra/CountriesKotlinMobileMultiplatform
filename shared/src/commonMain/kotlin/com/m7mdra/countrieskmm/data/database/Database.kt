package com.m7mdra.countrieskmm.data.database

import com.example.AppDatabase
import com.example.Country
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    val countryQueries = database.countryQueries

    fun insert(country: Country) {
        country.apply {


            countryQueries.insert(
                name = name,
                alpha2Code = alpha2Code,
                alpha3Code = alpha3Code,
                nativeName = nativeName,
                altSpellings = Json.encodeToString(altSpellings),
                area = area,
                borders = borders,
                callingCodes = callingCodes,
                capital = capital,
                cioc = cioc ?: "",
                currencies = currencies,
                demonym = demonym,
                gini = gini,
                languages = languages,
                latlng = latlng,
                numericCode = numericCode ?: "",
                population = population,
                region = region,
                regionalBlocs = regionalBlocs,
                subregion = subregion,
                timezones = timezones,
                topLevelDomain = topLevelDomain,
                translations = translations,
                flag = ""
            )
        }
    }

    fun getCachedData(): List<Country> {
        return database.countryQueries.getCountries().executeAsList()
    }


}