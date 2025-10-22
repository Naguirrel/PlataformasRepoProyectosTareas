package com.naguirrel.plataslabs8_muchos.data.remote.mapper

import com.naguirrel.plataslabs8_muchos.data.Character
import com.naguirrel.plataslabs8_muchos.data.Location
import com.naguirrel.plataslabs8_muchos.data.remote.dto.CharacterDto
import com.naguirrel.plataslabs8_muchos.data.remote.dto.LocationDto

fun CharacterDto.toEntity() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    image = image
)

fun LocationDto.toEntity() = Location(
    id = id,
    name = name,
    type = type,
    dimension = dimension
)
