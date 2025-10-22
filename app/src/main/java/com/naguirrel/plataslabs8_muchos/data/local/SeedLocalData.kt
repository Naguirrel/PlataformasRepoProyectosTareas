package com.naguirrel.plataslabs8_muchos.data.local

import android.content.Context
import com.naguirrel.plataslabs8_muchos.data.Character
import com.naguirrel.plataslabs8_muchos.data.Location
import com.naguirrel.plataslabs8_muchos.data.CharacterDb
import com.naguirrel.plataslabs8_muchos.data.LocationDb

/**
 * Siembras iniciales usando INSERT REPLACE.
 * Llama a esta función en tu flujo de login/sincronización inicial.
 */
suspend fun seedLocalData(context: Context) {
    val db = AppDatabase.getInstance(context)

    val characters = CharacterDb().getAllCharacters().map {
        Character(
            id = it.id,
            name = it.name,
            status = it.status,
            species = it.species,
            gender = it.gender,
            image = it.image
        )
    }

    val locations = LocationDb().getAllLocations().map {
        Location(
            id = it.id,
            name = it.name,
            type = it.type,
            dimension = it.dimension
        )
    }

    // Inserta (reemplaza si ya existe)
    db.characterDao().insertAll(characters)
    db.locationDao().insertAll(locations)
}
