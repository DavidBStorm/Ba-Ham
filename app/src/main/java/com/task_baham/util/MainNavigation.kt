package com.task_baham.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.task_baham.ui.composable.home.HomeScreen
import com.task_baham.ui.composable.image.ImageScreen
import com.task_baham.ui.composable.player.PlayerScreen
import com.task_baham.viewModel.home.HomeViewModel


sealed class Screens(val route: String) {
    object Home : Screens("home")
    object VideoPlayer : Screens("player")
    object ImageDisplay : Screens("image")


}


@Composable
fun MainNavigation(
    paddingValues: PaddingValues,
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, paddingValues.calculateBottomPadding().div(2))
    ) {
        composable(Screens.Home.route) {
            HomeScreen(homeViewModel, navController)
        }
        composable(Screens.VideoPlayer.route) {
            PlayerScreen(navController)
        }
        composable(Screens.ImageDisplay.route) {
            ImageScreen(navController)
        }


    }
}

