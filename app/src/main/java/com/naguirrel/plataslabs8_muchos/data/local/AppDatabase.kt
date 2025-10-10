package com.naguirrel.plataslabs8_muchos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naguirrel.plataslabs8_muchos.data.local.dao.CharacterDao
import com.naguirrel.plataslabs8_muchos.data.local.dao.LocationDao
import com.naguirrel.plataslabs8_muchos.data.local.entity.Character
import com.naguirrel.plataslabs8_muchos.data.local.entity.Location

@Database(
    entities = [Character::class, Location::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
}
