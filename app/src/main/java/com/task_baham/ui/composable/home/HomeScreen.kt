package com.task_baham.ui.composable.home

import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.task_baham.ui.composable.universal.DisplayLoading
import com.task_baham.util.GridItemSpan
import com.task_baham.util.getHeightOfScreenInDp
import com.task_baham.util.isVideo
import com.task_baham.viewModel.home.HomeViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    Log.e("TAG", "HomeScreen:  ")
    LaunchedEffect(Unit) {
//        homeViewModel.getMedia()
    }

    val media = remember { homeViewModel.getMedia() }
    val mediaLazyItems = media.collectAsLazyPagingItems()

//    val mediaLazyItems = homeViewModel.getMedia().collectAsLazyPagingItems()

//    val a =  getAllMediaFilesOnDevice(homeViewModel.getApp())
    Log.e("TAG", "HomeScreen: -- ${mediaLazyItems.itemCount}")
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(150.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(GridItemSpan),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                mediaLazyItems.itemCount,
//                key = {
//                    mediaLazyItems.itemSnapshotList.items[it]
//                }
            ) {

                val item = mediaLazyItems.itemSnapshotList.items[it]
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(getHeightOfScreenInDp().div(5))
                        .background(Color.Green)
                        .clickable {
                            Log.e("TAG", "HomeScreen: ${item.name}")
                        }
                ) {


                    if (item.isVideo()) {

                        val bitmap = ThumbnailUtils.createVideoThumbnail(item.path, MediaStore.Video.Thumbnails.MICRO_KIND)

                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(0.5.dp),
                            model = bitmap,
                            contentDescription = "video thumbnail",
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        val model = ImageRequest.Builder(homeViewModel.getAppContext())
                            .data(item)
                            .build()

                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(0.5.dp),
                            model = model,
                            contentDescription = "video thumbnail",
                            contentScale = ContentScale.Crop
                        )
                    }

                }
            }

            when (mediaLazyItems.loadState.refresh) { //FIRST LOAD
                is LoadState.Loading -> { // Loading UI
                    item(span = { GridItemSpan(4) }) {
                        DisplayLoading()
                    }
                }

                else -> {}
            }

            when (mediaLazyItems.loadState.append) { // Pagination
                is LoadState.Loading -> { // Pagination Loading UI
                    item(span = { GridItemSpan(4) }) {
                        DisplayLoading()

                    }
                }

                else -> {}
            }
        }
    }
}

