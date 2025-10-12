package com.naguirrel.plataslabs8_muchos.data.repo

import android.content.Context
import com.naguirrel.plataslabs8_muchos.data.local.DbProvider
import com.naguirrel.plataslabs8_muchos.data.local.entity.Location
import kotlinx.coroutines.flow.Flow

class LocationRepository(context: Context) {
    private val dao = DbProvider.get(context).locationDao()
    fun getAll(): Flow<List<Location>> = dao.getAll()
    fun getById(id: Int): Flow<Location> = dao.getById(id)
    suspend fun saveAll(items: List<Location>) = dao.upsertAll(items)
    suspend fun count(): Int = dao.count()
}
