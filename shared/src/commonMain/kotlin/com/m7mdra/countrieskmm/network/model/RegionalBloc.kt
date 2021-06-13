package com.m7mdra.myapplication.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegionalBloc(
    @SerialName("acronym")
    val acronym: String,
    @SerialName("name")
    val name: String,
    @SerialName("otherAcronyms")
    val otherAcronyms: List<String>,
    @SerialName("otherNames")
    val otherNames: List<String>
)