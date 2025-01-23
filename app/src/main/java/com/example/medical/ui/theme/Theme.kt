package com.example.medical.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


// Light Theme Colors
private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = PrimaryVariant,
    background = White,
    surface = White,
    surfaceTint = GrayLight,
    onPrimary = White,    // Text/icons on primary
    onBackground = GrayDark,
    onSurface = GrayDark
)

// Dark Theme Colors (Optional)
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    secondary = PrimaryVariant,
    background = Black,
    surface = GrayDark,
    surfaceTint = GrayLight,
    onPrimary = Black,
    onBackground = White,
    onSurface = White
)

@Composable
fun MedicalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}