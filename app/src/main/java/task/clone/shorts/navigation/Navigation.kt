package task.clone.shorts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import task.clone.shorts.presentation.AllShorts
import task.clone.shorts.presentation.ShortsUI

@Composable
fun NavController(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(
            route = "${Screen.ShortsUI.route}/{postId}",
            arguments = listOf(navArgument(name = "postId") {
                type = NavType.StringType
                nullable = false
            })
        ) {
            val postId = it.arguments?.getString("postId") ?: ""
            ShortsUI(postId, navController)
        }
        composable(Screen.AllShorts.route) {
            AllShorts(navHostController = navController)
        }
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
    }

}