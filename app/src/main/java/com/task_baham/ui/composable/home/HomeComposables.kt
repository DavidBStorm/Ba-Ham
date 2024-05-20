package com.task_baham.ui.composable.home

import android.content.Context
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.task_baham.ui.composable.universal.DisplayLoading
import com.task_baham.util.getHeightOfScreenInDp
import com.task_baham.util.isVideo
import java.io.File

@Composable
fun DisplayThumbs(mediaLazyItems: LazyPagingItems<File>, index: Int, appContext: Context) {
    val item = mediaLazyItems.itemSnapshotList.items[index]
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

            val bitmap = ThumbnailUtils.createVideoThumbnail(
                item.path,
                MediaStore.Video.Thumbnails.MICRO_KIND
            )

            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.5.dp),
                model = bitmap,
                contentDescription = "video thumbnail",
                contentScale = ContentScale.Crop
            )
        } else {
            val model = ImageRequest.Builder(appContext)
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

@Composable
fun DisplayLoadingDependOnState(
    mediaLazyItems: LazyPagingItems<File>,
    lazyGridScope: LazyGridScope
) {
    lazyGridScope.apply {
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
