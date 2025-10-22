package com.naguirrel.plataslabs8_muchos.data.repo

import com.naguirrel.plataslabs8_muchos.data.Location
import com.naguirrel.plataslabs8_muchos.data.local.dao.LocationDao
import com.naguirrel.plataslabs8_muchos.data.remote.RmApi
import com.naguirrel.plataslabs8_muchos.data.remote.mapper.toEntity
import kotlinx.coroutines.flow.Flow

class LocationRepository(
    private val api: RmApi,
    private val dao: LocationDao
) {
    fun observeAll(): Flow<List<Location>> = dao.getAll()

    fun observeById(id: Int): Flow<Location?> = dao.getById(id)

    suspend fun ensureSeeded() {
        if (dao.count() == 0) {
            val remote = api.getLocations()
            val entities = remote.results.map { it.toEntity() }
            dao.insertAll(entities)
        }
    }
}