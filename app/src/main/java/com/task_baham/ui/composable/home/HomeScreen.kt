package com.task_baham.ui.composable.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.task_baham.ui.composable.universal.DisplayLoading
import com.task_baham.util.GridItemSpan
import com.task_baham.util.PickerTxtForDisplay
import com.task_baham.util.getHeightOfScreenInDp
import com.task_baham.viewModel.home.HomeViewModel
import java.io.File


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {


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
//                    mediaLazyItems.itemSnapshotList.items[it]
//                }
            ) {
                DisplayThumbs(
                    mediaLazyItems = mediaLazyItems,
                    index = it,
                    appContext = homeViewModel.getAppContext()
                )

            }
            item {

                DisplayLoadingDependOnState(
                    mediaLazyItems = mediaLazyItems,
                    lazyGridScope = this@LazyVerticalGrid
                )

            }


        }
    }
}


