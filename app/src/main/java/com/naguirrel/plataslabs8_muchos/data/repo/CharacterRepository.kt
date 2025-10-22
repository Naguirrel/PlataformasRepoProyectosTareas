package com.naguirrel.plataslabs8_muchos.data.repo

import com.naguirrel.plataslabs8_muchos.data.Character
import com.naguirrel.plataslabs8_muchos.data.local.dao.CharacterDao
import com.naguirrel.plataslabs8_muchos.data.remote.RmApi
import com.naguirrel.plataslabs8_muchos.data.remote.mapper.toEntity
import kotlinx.coroutines.flow.Flow

class CharacterRepository(
    private val api: RmApi,
    private val dao: CharacterDao
) {
    fun observeAll(): Flow<List<Character>> = dao.getAll()

    fun observeById(id: Int): Flow<Character?> = dao.getById(id)

    // Offline First: si DB vacía, trae del API y persiste
    suspend fun ensureSeeded() {
        if (dao.count() == 0) {
            val remote = api.getCharacters()           // 20 por defecto
            val entities = remote.results.map { it.toEntity() }
            dao.insertAll(entities)
        }
    }
}
