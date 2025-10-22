package com.naguirrel.plataslabs8_muchos.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationListResponse(
    val info: PageInfo,
    val results: List<LocationDto>
)

@Serializable
data class LocationDto(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)
