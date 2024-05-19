package com.task_baham.ui.composable.home

import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.task_baham.ui.composable.universal.DisplayProgressbar
import com.task_baham.viewModel.home.HomeViewModel


@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    LaunchedEffect(Unit) {
        homeViewModel.getAllMedia()
    }
    val shouldShowDialog = homeViewModel.showProgressBar.collectAsState()
    val media = homeViewModel.getMedia.collectAsState()
    if (shouldShowDialog.value)
        DisplayProgressbar(state = shouldShowDialog)

    Column(Modifier.fillMaxSize()) {
        LazyColumn {
            items(media.value.size) {

                Column(
                    modifier = Modifier
                        .height(100.dp)
                        .background(Color.Green)
                ) {
                    val item = media.value[it]
                    val model = ImageRequest.Builder(homeViewModel.getAppContext())
                        .data(item)
                        .build()
                    AsyncImage(
                        modifier = Modifier.size(60.dp),
                        model = model,
                        contentDescription = "video thumbnail",
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier
                        .size(12.dp)
                        .background(Color.Black))
                }
            }
        }
    }
}

