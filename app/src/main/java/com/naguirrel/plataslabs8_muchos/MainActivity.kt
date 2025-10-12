package com.naguirrel.plataslabs8_muchos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.naguirrel.plataslabs8_muchos.ui.AppNav
import com.naguirrel.plataslabs8_muchos.ui.theme.RMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RMTheme {
                AppNav()
            }
        }
    }
}
