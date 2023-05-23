package task.clone.shorts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import task.clone.shorts.navigation.NavController
import task.clone.shorts.presentation.AllShorts
import task.clone.shorts.presentation.Demo
import task.clone.shorts.presentation.ShortsUI
import task.clone.shorts.ui.theme.ShortsCloneTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShortsCloneTheme {
                val navController = rememberNavController()
                    NavController(navController)

//            ShortsUI()
//                AllShorts()
            }
        }
    }
}
