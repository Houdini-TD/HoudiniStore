package ru.mobileup.template.core.theme.custom

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColors(
    val isLight: Boolean,
    val background: BackgroundColors,
    val text: TextColors,
    val icon: IconColors,
    val button: ButtonColors
)

data class BackgroundColors(
    val primary: Color,
    val card: Color,
    val green: Color,
    val red: Color,
    val selected: Color,
    val interfacePanel: Color
)

data class TextColors(
    val primary: Color,
    val secondary: Color,
    val invert: Color
)

data class IconColors(
    val primary: Color,
    val secondary: Color,
    val invert: Color
)

data class ButtonColors(
    val primary: Color,
    val secondary: Color
)

data class TextFieldColors(
    val primary: Color,
    val secondary: Color
)


val LocalCustomColors = staticCompositionLocalOf<CustomColors?> { null }
