package task.clone.shorts.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap

@Composable
fun Grapple(modifier: Modifier = Modifier, color: Color = Color.DarkGray) {
    Canvas(
        modifier = modifier,
        onDraw = {
            drawLine(
                color = color,
                start = Offset(size.width / 10, size.height / 2f),
                end = Offset(size.width - size.width / 10, size.height / 2f),
                strokeWidth = size.height / 3,
                cap = StrokeCap.Round,
            )
        }
    )
}