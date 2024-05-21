package com.task_baham.ui.composable.image

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.task_baham.util.ContentDescriptionThumbs
import com.task_baham.util.NavigationKey
import java.io.File

@Composable
fun ImageScreen(navController: NavHostController) {
    BackHandler {
        navController.popBackStack()
    }
    Column(modifier= Modifier
        .fillMaxSize()
        .background(Color.LightGray)) {

        val backStackEntry = navController.previousBackStackEntry
        val savedStateHandle = backStackEntry?.savedStateHandle
        val filePath = File(savedStateHandle?.get<String>(NavigationKey)?:"")


        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.5.dp),
            model = filePath,
            contentDescription = ContentDescriptionThumbs,
            contentScale = ContentScale.Inside
        )
    }
}