package com.m7mdra.myapplication.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Translations(
    @SerialName("br")
    val br: String?,
    @SerialName("de")
    val de: String?,
    @SerialName("es")
    val es: String?,
    @SerialName("fa")
    val fa: String?,
    @SerialName("fr")
    val fr: String?,
    @SerialName("hr")
    val hr: String?,
    @SerialName("it")
    val it: String?,
    @SerialName("ja")
    val ja: String?,
    @SerialName("nl")
    val nl: String?,
    @SerialName("pt")
    val pt: String?
)