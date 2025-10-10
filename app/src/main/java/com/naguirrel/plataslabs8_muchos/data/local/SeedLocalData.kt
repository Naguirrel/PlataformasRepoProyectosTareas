package com.naguirrel.plataslabs8_muchos.data.local

import android.content.Context
import com.naguirrel.plataslabs8_muchos.data.CharacterDb
import com.naguirrel.plataslabs8_muchos.data.LocationDb
import com.naguirrel.plataslabs8_muchos.data.local.entity.Character
import com.naguirrel.plataslabs8_muchos.data.local.entity.Location

suspend fun seedLocalData(context: Context) {
    val db = DbProvider.get(context)

    val characters = CharacterDb().getAllCharacters().map {
        Character(it.id, it.name, it.status, it.species, it.gender, it.image)
    }
    val locations = LocationDb().getAllLocations().map {
        Location(it.id, it.name, it.type, it.dimension)
    }

    db.characterDao().upsertAll(characters)
    db.locationDao().upsertAll(locations)
}
