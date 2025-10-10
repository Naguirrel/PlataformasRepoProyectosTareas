package com.naguirrel.plataslabs8_muchos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class Location(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)
