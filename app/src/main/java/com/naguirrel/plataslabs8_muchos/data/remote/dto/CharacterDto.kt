package com.naguirrel.plataslabs8_muchos.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterListResponse(
    val info: PageInfo,
    val results: List<CharacterDto>
)

@Serializable
data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String? = null,
    val prev: String? = null
)

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)
