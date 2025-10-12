package com.naguirrel.plataslabs8_muchos.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.naguirrel.plataslabs8_muchos.data.session.UserPrefs
import kotlinx.coroutines.flow.Flow

class ProfileViewModel(app: Application) : AndroidViewModel(app) {
    private val prefs = UserPrefs(app)
    val nameFlow: Flow<String?> = prefs.nameFlow
    suspend fun logout() = prefs.clearName()
}
