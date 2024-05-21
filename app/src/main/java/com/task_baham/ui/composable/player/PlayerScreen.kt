package com.task_baham.ui.composable.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.navigation.NavHostController
import com.task_baham.util.NavigationKey
import java.io.File

@Composable
fun PlayerScreen(navController: NavHostController) {

    val backStackEntry = navController.previousBackStackEntry
    val savedStateHandle = backStackEntry?.savedStateHandle
    val file = File(savedStateHandle?.get<String>(NavigationKey) ?: "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        VideoPlayer(file = file, navController = navController)
    }
}