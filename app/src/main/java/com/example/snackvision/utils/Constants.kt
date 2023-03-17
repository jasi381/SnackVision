package com.example.snackvision.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.core.graphics.toColorInt
import com.example.snackvision.R

object Constants {
    private const val splashScreenBackgroundCode ="#FDD36A"
    val splashScreenBackgroundColor = Color(splashScreenBackgroundCode.toColorInt())

    val TextFontFamily = FontFamily(
        Font(R.font.poppins_medium_italic),
    )
}