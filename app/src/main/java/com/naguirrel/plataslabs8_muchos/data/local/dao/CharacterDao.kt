package com.naguirrel.plataslabs8_muchos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.naguirrel.plataslabs8_muchos.data.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Character>)

    @Query("SELECT * FROM characters ORDER BY id")
    fun getAll(): Flow<List<Character>>

    @Query("SELECT COUNT(*) FROM characters")
    suspend fun count(): Int

    @Query("SELECT * FROM characters WHERE id = :id LIMIT 1")
    fun getById(id: Int): Flow<Character?>
}
