package ru.mobileup.template.core.theme.values

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import ru.mobileup.template.core.theme.custom.BodyTypography
import ru.mobileup.template.core.theme.custom.ButtonTypography
import ru.mobileup.template.core.theme.custom.CaptionTypography
import ru.mobileup.template.core.theme.custom.CustomTypography
import ru.mobileup.template.core.theme.custom.TextFieldTypography
import ru.mobileup.template.core.theme.custom.TitleTypography

val AppTypography = CustomTypography(
    title = TitleTypography(
        regular = TextStyle(
            fontSize = 24.sp
        ),
        h1 = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ),
        h2 = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.1.em
        )
    ),
    body = BodyTypography(
        regular = TextStyle(
            fontSize = 16.sp
        )
    ),
    caption = CaptionTypography(
        regular = TextStyle(
            fontSize = 12.sp
        ),
        bold = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    ),
    button = ButtonTypography(
        bold = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    ),
    textField = TextFieldTypography(
        regular = TextStyle(
            fontSize = 16.sp
        )
    )
)