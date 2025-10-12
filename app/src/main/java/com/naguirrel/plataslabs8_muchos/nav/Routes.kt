package com.naguirrel.plataslabs8_muchos.nav

import kotlinx.serialization.Serializable

@Serializable object SplashRoute
@Serializable object LoginRoute
@Serializable object HomeRoute
@Serializable object CharactersRoute
@Serializable data class CharacterDetailRoute(val id: Int)
@Serializable object LocationsRoute
@Serializable data class LocationDetailRoute(val id: Int)
@Serializable object ProfileRoute
