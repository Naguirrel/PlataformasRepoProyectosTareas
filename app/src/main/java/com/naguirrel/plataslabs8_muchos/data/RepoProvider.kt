package com.naguirrel.plataslabs8_muchos.data

import android.content.Context
import com.naguirrel.plataslabs8_muchos.data.local.AppDatabase
import com.naguirrel.plataslabs8_muchos.data.remote.RmApi
import com.naguirrel.plataslabs8_muchos.data.repo.CharacterRepository
import com.naguirrel.plataslabs8_muchos.data.repo.LocationRepository

object RepoProvider {
    private val api: RmApi by lazy { RmApi.create() }

    fun characters(context: Context): CharacterRepository {
        val db = AppDatabase.getInstance(context)
        return CharacterRepository(api, db.characterDao())
    }

    fun locations(context: Context): LocationRepository {
        val db = AppDatabase.getInstance(context)
        return LocationRepository(api, db.locationDao())
    }
}
