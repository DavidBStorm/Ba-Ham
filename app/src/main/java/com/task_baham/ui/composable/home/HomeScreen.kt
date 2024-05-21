package com.task_baham.ui.composable.home

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.task_baham.ui.composable.universal.DisplayLoading
import com.task_baham.util.GridItemSpan
import com.task_baham.util.NavigationKey
import com.task_baham.util.PickerTxtForDisplay
import com.task_baham.util.Screens
import com.task_baham.util.getHeightOfScreenInDp
import com.task_baham.util.isVideo
import com.task_baham.viewModel.home.HomeViewModel


@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navController: NavHostController) {


    val media = remember { homeViewModel.getMedia() }
    val mediaLazyItems = media.collectAsLazyPagingItems()


    Column(Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(getHeightOfScreenInDp().div(6))
                .background(Color.White),
        ) {

            Text(
                text = PickerTxtForDisplay,
                modifier = Modifier
                    .align(Alignment.Center),
                textAlign = TextAlign.Center
            )

        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(GridItemSpan),
            modifier = Modifier.fillMaxWidth()
        ) {

            items(
                mediaLazyItems.itemCount,
//                key = {
//                    mediaLazyItems.itemSnapshotList.items[it].name.hashCode()
//                }
            ) {
                DisplayThumbs(
                    mediaLazyItems = mediaLazyItems,
                    index = it,
                    appContext = homeViewModel.getAppContext(),
                    onItemClick = { file ->
                        val backStackEntry = navController.currentBackStackEntry
                        val savedStateHandle = backStackEntry?.savedStateHandle
                        savedStateHandle?.set(NavigationKey, file.path)

                        navController.navigate(
                            if (file.isVideo()) Screens.VideoPlayer.route else Screens.ImageDisplay.route,
                        )
                    }
                )

            }
            when (mediaLazyItems.loadState.refresh) { //FIRST LOAD
                is LoadState.Loading -> {
                    item(span = { GridItemSpan(GridItemSpan) }) {
                        DisplayLoading()
                    }
                }

                else -> {}
            }

            when (mediaLazyItems.loadState.append) { // Pagination
                is LoadState.Loading -> {
                    item(span = { GridItemSpan(GridItemSpan) }) {
                        DisplayLoading()

                    }
                }

                else -> {}
            }


        }
    }
}


