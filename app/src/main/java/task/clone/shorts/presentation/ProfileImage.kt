package task.clone.shorts.presentation
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.min
import coil.compose.AsyncImage
import task.clone.shorts.ui.theme.iconColor
import task.clone.shorts.ui.theme.shortsBackground

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: Any? = null,
    initial: Char? = null,
    onClick: (() -> Unit)? = null
) {
    if (imageUrl != null) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Display Image",
            modifier = modifier
                .then(
                    onClick?.let {
                        Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { it.invoke() }
                    } ?: run {
                        Modifier
                    }
                ),
            contentScale = ContentScale.Crop
        )
    } else {
        BoxWithConstraints(
            modifier = modifier
                .then(
                    onClick?.let {
                        Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { it.invoke() }
                    } ?: run {
                        Modifier
                    }
                )
                .background(shortsBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initial?.uppercase() ?: "",
                fontWeight = FontWeight.SemiBold,
                color = iconColor
            )
        }
    }
}