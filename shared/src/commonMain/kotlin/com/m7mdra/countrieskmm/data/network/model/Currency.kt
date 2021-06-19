package com.m7mdra.countrieskmm.data.network.model


import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class  Currency constructor(

    @SerialName("code")
    val code: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("symbol")
    val symbol: String?
)