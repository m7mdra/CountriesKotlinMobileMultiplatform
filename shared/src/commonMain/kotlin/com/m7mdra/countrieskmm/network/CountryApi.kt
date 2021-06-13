package com.m7mdra.myapplication.network

import com.m7mdra.countrieskmm.network.model.Country
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class CountryApi {
    companion object {
        private const val ENDPOINT = "https://restcountries.eu/rest/v2"
        private const val ALL_COUNTRIES = "$ENDPOINT/all"
    }

    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getAll(): List<Country> {
        return httpClient.get(ALL_COUNTRIES)
    }
}