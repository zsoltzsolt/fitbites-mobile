package com.example.fitbites.ui.theme

import com.example.fitbites.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Poppins = FontFamily(
    listOf(
        Font(resId = R.font.poppins_medium, weight = FontWeight.Medium),
        Font(resId = R.font.poppins_light, weight = FontWeight.Light),
        Font(resId = R.font.poppins_bold, weight = FontWeight.Bold),
        Font(resId = R.font.poppins_semi_bold, weight = FontWeight.SemiBold)
    )
)

val Inter = FontFamily(
    listOf(
        Font(resId = R.font.inter_regular, weight = FontWeight.Normal),
        Font(resId = R.font.inter_semi_bold, weight = FontWeight.SemiBold),
        Font(resId = R.font.inter_light, weight = FontWeight.Light)
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Light,
        fontSize = 10.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Poppins,
        fontSize = 14.sp
    )


    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)