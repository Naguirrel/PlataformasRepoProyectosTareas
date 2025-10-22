package com.naguirrel.plataslabs8_muchos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.naguirrel.plataslabs8_muchos.data.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Location>)

    @Query("SELECT * FROM locations ORDER BY id")
    fun getAll(): Flow<List<Location>>

    @Query("SELECT COUNT(*) FROM locations")
    suspend fun count(): Int

    @Query("SELECT * FROM locations WHERE id = :id LIMIT 1")
    fun getById(id: Int): Flow<Location?>
}
