package com.naguirrel.plataslabs8_muchos.data.local.dao

import androidx.room.*
import com.naguirrel.plataslabs8_muchos.data.local.entity.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<Location>)

    @Query("SELECT * FROM locations ORDER BY name")
    fun getAll(): Flow<List<Location>>

    @Query("SELECT * FROM locations WHERE id = :id")
    fun getById(id: Int): Flow<Location>

    @Query("SELECT COUNT(*) FROM locations")
    suspend fun count(): Int
}
