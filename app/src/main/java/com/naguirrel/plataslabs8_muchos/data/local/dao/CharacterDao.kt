package com.naguirrel.plataslabs8_muchos.data.local.dao

import androidx.room.*
import com.naguirrel.plataslabs8_muchos.data.local.entity.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<Character>)

    @Query("SELECT * FROM characters ORDER BY name")
    fun getAll(): Flow<List<Character>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getById(id: Int): Flow<Character>

    @Query("SELECT COUNT(*) FROM characters")
    suspend fun count(): Int
}
