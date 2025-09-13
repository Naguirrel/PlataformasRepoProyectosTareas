package com.naguirrel.plataslabs8_muchos.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = md_light_primary,
    secondary = md_light_secondary,
    background = md_light_background,
    surface = md_light_surface,
    onPrimary = md_light_onPrimary,
    onBackground = md_light_onBackground,
)

private val DarkColors = darkColorScheme(
    primary = md_dark_primary,
    secondary = md_dark_secondary,
    background = md_dark_background,
    surface = md_dark_surface,
    onPrimary = md_dark_onPrimary,
    onBackground = md_dark_onBackground,
)

@Composable
fun RMTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val ctx = LocalContext.current
            if (useDarkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
        } else {
            if (useDarkTheme) DarkColors else LightColors
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(), // usa tipografía por defecto
        content = content
    )
}
