package com.m7mdra.countrieskmm.network.model


import com.m7mdra.myapplication.network.model.Currency
import com.m7mdra.countrieskmm.data.network.model.Language
import com.m7mdra.myapplication.network.model.RegionalBloc
import com.m7mdra.myapplication.network.model.Translations
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
