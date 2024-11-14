package com.example.fitbites.ui.theme

import androidx.compose.ui.graphics.Color

val RacingGreen = Color(0xFF141718)
val SmokeyGrey = Color(0xFF757171)
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val Alabaster = Color(0xFFFAFAFA)

sealed class ThemeColors(
    val background: Color,
    val text: Color,
    val textSecondary: Color
) {
    object Dark: ThemeColors(
        background = RacingGreen,
        text = Color.White,
        textSecondary = SmokeyGrey
    )
    object Light: ThemeColors(
        background = Alabaster,
        text = Color.Black,
        textSecondary = SmokeyGrey
    )
}