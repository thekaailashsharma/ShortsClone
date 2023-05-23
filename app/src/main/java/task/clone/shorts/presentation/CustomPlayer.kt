package task.clone.shorts.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import task.clone.shorts.R
import task.clone.shorts.ShortsViewModel
import task.clone.shorts.ui.theme.iconColor
import java.util.concurrent.TimeUnit


@SuppressLint("UnsafeOptInUsageError")
@Composable
fun VideoView(
    videoUri: String,
    onDoubleClick: () -> Unit,
    shortsViewModel: ShortsViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(
                MediaItem.fromUri(
                    Uri.parse(videoUri)
                )
            )
            prepare()
            playWhenReady = true
            shortsViewModel.isLoading.value = this.isLoading

        }
    }
    var shouldShowControls by remember { mutableStateOf(false) }

    var isPlaying by remember { mutableStateOf(exoPlayer.isPlaying) }

    var totalDuration by remember { mutableStateOf(0L) }

    var currentTime by remember { mutableStateOf(0L) }

    var bufferedPercentage by remember { mutableStateOf(0) }

    var playbackState by remember { mutableStateOf(exoPlayer.playbackState) }

    if (shortsViewModel.isLoading.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            val currenanim by rememberLottieComposition(
                spec = LottieCompositionSpec.Asset("load.json")
            )
            LottieAnimation(
                composition = currenanim,
                iterations = Int.MAX_VALUE,
                contentScale = ContentScale.Crop,
                speed = 0.75f,
                modifier = Modifier
                    .size(200.dp)
            )
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            DisposableEffect(key1 = Unit) {
                val listener =
                    object : Player.Listener {
                        override fun onEvents(
                            player: Player,
                            events: Player.Events
                        ) {
                            super.onEvents(player, events)
                            totalDuration = player.duration.coerceAtLeast(0L)
                            currentTime = player.currentPosition.coerceAtLeast(0L)
                            bufferedPercentage = player.bufferedPercentage
                            isPlaying = player.isPlaying
                            playbackState = player.playbackState
                        }
                    }

                exoPlayer.addListener(listener)

                onDispose {
                    exoPlayer.removeListener(listener)
                    exoPlayer.release()
                }
            }

            AndroidView(
                modifier =
                Modifier
                    .clickable {
                        shouldShowControls = shouldShowControls.not()
                    }
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures(onDoubleTap = {
                            onDoubleClick()
                        })
                    },
                factory = {
                    PlayerView(context).apply {
                        player = exoPlayer
                        useController = false
                        layoutParams =
                            FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL

                    }
                }
            )

            PlayerControls(
                modifier = Modifier.fillMaxSize(),
                isVisible = { shouldShowControls },
                totalDuration = { totalDuration },
                currentTime = { currentTime },
                bufferedPercentage = { bufferedPercentage },
                onSeekChanged = { timeMs: Float ->
                    exoPlayer.seekTo(timeMs.toLong())
                }
            )
        }
    }
}
@Composable
fun BottomControls(
    modifier: Modifier = Modifier,
    totalDuration: () -> Long,
    currentTime: () -> Long,
    bufferPercentage: () -> Int,
    onSeekChanged: (timeMs: Float) -> Unit
) {

    val duration = remember(totalDuration()) { totalDuration() }

    val videoTime = remember(currentTime()) { currentTime() }

    Column(modifier = modifier.padding(bottom = 5.dp)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = videoTime.toFloat(),
                onValueChange = onSeekChanged,
                valueRange = 0f..duration.toFloat(),
                colors =
                SliderDefaults.colors(
                    thumbColor = Color.Black,
                    activeTickColor = Color.Black
                )
            )
        }
    }
}



@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun PlayerControls(
    modifier: Modifier = Modifier,
    isVisible: () -> Boolean,
    totalDuration: () -> Long,
    currentTime: () -> Long,
    bufferedPercentage: () -> Int,
    onSeekChanged: (timeMs: Float) -> Unit
) {

    val visible = remember(isVisible()) { isVisible() }

    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(modifier = Modifier.background(Color.Black.copy(alpha = 0.6f))) {
            BottomControls(
                modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .animateEnterExit(
                        enter =
                        slideInVertically(
                            initialOffsetY = { fullHeight: Int ->
                                fullHeight
                            }
                        ),
                        exit =
                        slideOutVertically(
                            targetOffsetY = { fullHeight: Int ->
                                fullHeight
                            }
                        )
                    ),
                totalDuration = totalDuration,
                currentTime = currentTime,
                bufferPercentage = bufferedPercentage,
                onSeekChanged = onSeekChanged
            )
        }
    }
}

