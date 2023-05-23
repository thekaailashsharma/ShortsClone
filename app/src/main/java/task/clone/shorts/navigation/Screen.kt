package task.clone.shorts.navigation

sealed class Screen(val route: String){
    object ShortsUI: Screen("shorts_ui")
    object AllShorts: Screen("all_shorts")
    object SplashScreen: Screen("splash_screen")
}