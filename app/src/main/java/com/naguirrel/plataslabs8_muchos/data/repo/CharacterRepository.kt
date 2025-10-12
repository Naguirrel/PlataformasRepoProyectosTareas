package com.naguirrel.plataslabs8_muchos.data.repo

import android.content.Context
import com.naguirrel.plataslabs8_muchos.data.local.DbProvider
import com.naguirrel.plataslabs8_muchos.data.local.entity.Character
import kotlinx.coroutines.flow.Flow

class CharacterRepository(context: Context) {
    private val dao = DbProvider.get(context).characterDao()
    fun getAll(): Flow<List<Character>> = dao.getAll()
    fun getById(id: Int): Flow<Character> = dao.getById(id)
    suspend fun saveAll(items: List<Character>) = dao.upsertAll(items)
    suspend fun count(): Int = dao.count()
}
