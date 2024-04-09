package core.utils

import android.graphics.BlurMaskFilter
import android.os.Build
import android.os.Build.VERSION_CODES.Q
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.advancedShadow(
    blur: Dp = 0.dp,
    cornersRadius: Dp = 0.dp,
    spread: Dp = 0.dp,
    color: Color = Color.Black,
    alpha: Float = 1f,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
): Modifier {

    return drawBehind {

        val shadowColor = color.copy(alpha = alpha).toArgb()
        val transparentColor = color.copy(alpha = 0f).toArgb()

        drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            if(blur > 0.dp){
                if (Build.VERSION.SDK_INT >= Q) {
                    frameworkPaint.color = transparentColor
                    frameworkPaint.setShadowLayer(
                        blur.toPx(),
                        offsetX.toPx(),
                        offsetY.toPx(),
                        shadowColor
                    )
                } else {
                    frameworkPaint.color = shadowColor
                    frameworkPaint.maskFilter =
                        (BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL))
                }
            }

            it.drawRoundRect(
                -spread.toPx(),
                -spread.toPx(),
                this.size.width + spread.toPx(),
                this.size.height + spread.toPx(),
                cornersRadius.toPx(),
                cornersRadius.toPx(),
                paint
            )
        }
    }
}