package com.task_baham.ui.composable.home

import androidx.compose.foundation.background
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
import com.task_baham.viewModel.home.HomeViewModel
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    LaunchedEffect(Unit) {
        homeViewModel.getMedia()
    }
    val mediaLazyItems = homeViewModel.getMedia().collectAsLazyPagingItems()

    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(150.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(GridItemSpan),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(mediaLazyItems.itemCount,
                key = {
                    mediaLazyItems.itemSnapshotList.items[it]
                }
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(getHeightOfScreenInDp().div(5))
                        .background(Color.Green)
                ) {
                    val item = mediaLazyItems.itemSnapshotList.items[it]


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

