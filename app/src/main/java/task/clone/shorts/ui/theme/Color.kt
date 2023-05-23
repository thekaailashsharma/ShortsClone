package task.clone.shorts.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val isDarkThemeEnabled: Boolean
    @Composable
    get() = isSystemInDarkTheme()

val shortsBackground : Color
        @Composable
        get() = if (isDarkThemeEnabled) Color(0xFF0A0A0A) else Color(0xFFFFFFFF)

val iconColor : Color
        @Composable
        get() = if (isDarkThemeEnabled) Color.White else Color.Black
