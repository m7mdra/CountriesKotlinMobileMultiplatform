package com.m7mdra.myapplication.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Language(
    @SerialName("iso639_1")
    val iso6391: String?,
    @SerialName("iso639_2")
    val iso6392: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("nativeName")
    val nativeName: String?
)