package task.clone.shorts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import task.clone.shorts.presentation.Demo
import task.clone.shorts.ui.theme.ShortsCloneTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShortsCloneTheme {
            Demo()
            }
        }
    }
}
