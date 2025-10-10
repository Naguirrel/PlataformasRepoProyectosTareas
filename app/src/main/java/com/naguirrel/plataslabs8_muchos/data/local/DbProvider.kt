package com.naguirrel.plataslabs8_muchos.data.local

import android.content.Context
import androidx.room.Room

object DbProvider {
    @Volatile private var INSTANCE: AppDatabase? = null

    fun get(context: Context): AppDatabase =
        INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "rm.db"
            ).build().also { INSTANCE = it }
        }
}
