package com.m7mdra.countrieskmm.data.network.model


import com.m7mdra.myapplication.network.model.RegionalBloc
import com.m7mdra.myapplication.network.model.Translations
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

@Serializable
data class Country(
    @SerialName("alpha2Code")
    val alpha2Code: String,
    @SerialName("alpha3Code")
    val alpha3Code: String,
    @SerialName("altSpellings")
    val altSpellings: List<String>,
    @SerialName("area")
    val area: Double?,
    @SerialName("borders")
    val borders: List<String>,
    @SerialName("callingCodes")
    val callingCodes: List<String>,
    @SerialName("capital")
    val capital: String,
    @SerialName("cioc")
    val cioc: String?,
    @SerialName("currencies")
    val currencies: List<Currency>,
    @SerialName("demonym")
    val demonym: String,

    @SerialName("gini")
    val gini: Double?,
    @SerialName("languages")
    val languages: List<Language>,
    @SerialName("latlng")
    val latlng: List<Double>,
    @SerialName("name")
    val name: String,
    @SerialName("nativeName")
    val nativeName: String,
    @SerialName("numericCode")
    val numericCode: String?,
    @SerialName("population")
    val population: Int,
    @SerialName("region")
    val region: String,
    @SerialName("regionalBlocs")
    val regionalBlocs: List<RegionalBloc>,
    @SerialName("subregion")
    val subregion: String,
    @SerialName("timezones")
    val timezones: List<String>,
    @SerialName("topLevelDomain")
    val topLevelDomain: List<String>,
    @SerialName("translations")
    val translations: Translations
) {
    val flag: String
        get() = "https://flagcdn.com/h240/${alpha2Code.toLowerCase()}.png"
}

fun Country.map(): com.example.Country {
    return com.example.Country(
        name = name,
        alpha2Code = alpha2Code,
        alpha3Code = alpha3Code,
        nativeName = nativeName,
        altSpellings = Json.encodeToString(altSpellings),
        area = area,
        borders = Json.encodeToString(borders),
        callingCodes = Json.encodeToString(callingCodes),
        capital = capital,
        cioc = cioc ?: "",
        currencies = Json.encodeToString(currencies),
        demonym = demonym,
        gini = gini,
        languages = Json.encodeToString(languages),
        latlng = Json.encodeToString(latlng),
        numericCode = numericCode ?: "",
        population = population.toDouble(),
        region = region,
        regionalBlocs = Json.encodeToString(regionalBlocs),
        subregion = subregion,
        timezones = Json.encodeToString(timezones),
        topLevelDomain = Json.encodeToString(topLevelDomain),
        translations = Json.encodeToString(translations),
        flag = ""
    )
}

fun com.example.Country.map(): Country {
    return Country(
        name = name ?: "",
        alpha2Code = alpha2Code ?: "",
        alpha3Code = alpha3Code ?: "",
        nativeName = nativeName ?: "",
        altSpellings = Json.decodeFromString(altSpellings ?: "") ?: listOf(),
        area = area,
        borders = Json.decodeFromString(borders ?: "") ?: listOf(),
        callingCodes = Json.decodeFromString(callingCodes ?: "") ?: listOf(),
        capital = capital ?: "",
        cioc = cioc ?: "",
        currencies = Json.decodeFromString(currencies ?: "") ?: listOf(),
        demonym = demonym ?: "",
        gini = gini,
        languages = Json.decodeFromString(languages ?: "") ?: listOf(),
        latlng = Json.decodeFromString(latlng ?: "") ?: listOf(),
        numericCode = numericCode ?: "",
        population = population?.toInt() ?: 0,
        region = region ?: "",
        regionalBlocs = Json.decodeFromString(regionalBlocs ?: "") ?: listOf(),
        subregion = subregion ?: "",
        timezones = Json.decodeFromString(timezones ?: "") ?: listOf(),
        topLevelDomain = Json.decodeFromString(topLevelDomain ?: "") ?: listOf(),
        translations = Json.decodeFromString(Json.encodeToString(translations)),
    )
}
