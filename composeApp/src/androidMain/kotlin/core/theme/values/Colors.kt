package ru.mobileup.template.core.theme.values

import androidx.compose.ui.graphics.Color
import ru.mobileup.template.core.theme.custom.BackgroundColors
import ru.mobileup.template.core.theme.custom.ButtonColors
import ru.mobileup.template.core.theme.custom.CustomColors
import ru.mobileup.template.core.theme.custom.IconColors
import ru.mobileup.template.core.theme.custom.TextColors

val LightAppColors = CustomColors(
    isLight = true,
    background = BackgroundColors(
        primary = Color(0xFFFFFFFF),
        card = Color(0xFFBBD2E7),
        selected = Color(0xFF8285F1),
        interfacePanel = Color(0xFF172E69),
        green = Color(0xFF60994C),
        red = Color(0xFFC75D4A)
    ),
    text = TextColors(
        primary = Color(0xFF000000),
        secondary = Color(0xFF797979),
        invert = Color(0xFFFFFFFF),
    ),
    icon = IconColors(
        primary = Color(0xFF000000),
        secondary = Color(0xFF797979),
        invert = Color(0xFFFFFFFF),
    ),
    button = ButtonColors(
        primary = Color(0xFF514BA2),
        secondary = Color(0xFFFFFFFF),
    )
)

val DarkAppColors = LightAppColors

//val DarkAppColors = CustomColors(
//    isLight = false,
//    background = BackgroundColors(
//        primary = Color(0xFF000000),
//        secondary = Color(0xFF314659)
//    ),
//    text = TextColors(
//        primary = Color(0xFFCC0101), // Белый текст в темной теме
//        secondary = Color(0xFF797979),
//        invert = Color(0xFF000000),   // Черный текст в темной теме
//    ),
//    icon = IconColors(
//        primary = Color(0xFFFFFFFF),
//        secondary = Color(0xFF797979),
//        invert = Color(0xFF000000),
//    ),
//    button = ButtonColors(
//        primary = Color(0xFF516BA2),
//        secondary = Color(0xFFFFFFFF),
//    )
//)