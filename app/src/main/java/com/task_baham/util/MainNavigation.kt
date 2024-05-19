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
import com.task_baham.ui.composable.HomeScreen
import com.task_baham.viewModel.home.HomeViewModel
import com.task_baham.viewModel.main.MainViewModel


sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Player : Screens("player")



}


@Composable
fun MainNavigation(
    paddingValues: PaddingValues,
) {
    val homeViewModel: HomeViewModel = hiltViewModel()


    NavHost(
        navController = rememberNavController(),
        startDestination = Screens.Home.route,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, paddingValues.calculateBottomPadding().div(2))
    ) {
        composable(Screens.Home.route) {
            HomeScreen(homeViewModel)
        }
//        composable(Screens.Trade.route) {
//            TradeScreen(navController, tradeViewModel)
//        }


    }
}

