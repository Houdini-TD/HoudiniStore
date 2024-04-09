package ru.mobileup.template.core.theme.custom

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class CustomTypography(
    val title: TitleTypography,
    val body: BodyTypography,
    val caption: CaptionTypography,
    val button: ButtonTypography,
    val textField: TextFieldTypography
)

data class TitleTypography(
    val regular: TextStyle,
    val h1: TextStyle,
    val h2: TextStyle
)

data class BodyTypography(
    val regular: TextStyle
)

data class CaptionTypography(
    val regular: TextStyle,
    val bold: TextStyle
)

data class ButtonTypography(
    val bold: TextStyle
)

data class TextFieldTypography(
    val regular: TextStyle
)

val LocalCustomTypography = staticCompositionLocalOf<CustomTypography?> { null }
