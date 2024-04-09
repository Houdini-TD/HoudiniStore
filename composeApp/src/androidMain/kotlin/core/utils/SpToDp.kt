package core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun convertSpToDp(spValue: Float): Dp {
    val density = LocalDensity.current.density
    return remember(spValue) {
        (spValue * density).dp
    }
}