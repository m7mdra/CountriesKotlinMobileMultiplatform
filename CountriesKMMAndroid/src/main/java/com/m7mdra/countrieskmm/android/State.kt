package com.m7mdra.countrieskmm.android

import com.m7mdra.countrieskmm.data.network.model.Country

sealed class State
object LoadingState : State()
object ErrorState : State()
class Success(val list: List<Country>) : State()
