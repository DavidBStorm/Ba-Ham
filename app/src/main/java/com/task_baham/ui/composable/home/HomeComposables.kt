package com.task_baham.ui.composable.home

import android.content.Context
import android.media.MediaMetadataRetriever
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.task.baham.R
import com.task_baham.ui.composable.universal.DisplayLoading
import com.task_baham.util.ContentDescriptionThumbs
import com.task_baham.util.ContentDescriptionThumbsIcon
import com.task_baham.util.getHeightOfScreenInDp
import com.task_baham.util.iconPaddingValues
import com.task_baham.util.isVideo
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.io.File


@Composable
fun DisplayThumbsV3(
    mediaLazyItems: LazyPagingItems<File>,
    index: Int,
    appContext: Context,
    onItemClick: (File) -> Unit
) {
    val scope = rememberCoroutineScope()
    val item = mediaLazyItems.itemSnapshotList.items[index]
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(getHeightOfScreenInDp().div(5))
            .background(Color.Green)
            .clickable {
                onItemClick.invoke(item)
            }
    ) {
        val thumbnail = remember {
            var result: ImageBitmap? = null
            scope.launch {
                try {
                    val retriever = MediaMetadataRetriever()
                    retriever.setDataSource(appContext, item.toUri())
                    val bitmap = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST)
                    result = bitmap?.asImageBitmap()
                } catch (e: Exception) {
                    Log.e("eee", "DisplayThumbs: $e")
                }
            }
            result
        }

        if (item.isVideo()) {
            if (thumbnail != null)
                Image(
                    bitmap = thumbnail,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.5.dp),
                    contentDescription = ContentDescriptionThumbs,
                    contentScale = ContentScale.Crop
                )

        } else {
            val model = ImageRequest.Builder(appContext)
                .data(item)
                .build()
            Image(
                painter = rememberAsyncImagePainter(model),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.5.dp),
                contentDescription = ContentDescriptionThumbs,
                contentScale = ContentScale.Crop
            )

        }

        Image(
            painter = painterResource(id = if (item.isVideo()) R.drawable.ic_video else R.drawable.ic_image),
            contentDescription = ContentDescriptionThumbsIcon,
            modifier = Modifier
                .size(28.dp)
                .padding(iconPaddingValues)
                .align(Alignment.BottomEnd)
        )

    }
}


@Composable
fun DisplayThumbsV2(
    mediaLazyItems: LazyPagingItems<File>,
    index: Int,
    appContext: Context,
    onItemClick: (File) -> Unit
) {
    val scope = rememberCoroutineScope()
    val item = mediaLazyItems.itemSnapshotList.items[index]
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(getHeightOfScreenInDp().div(5))
            .background(Color.Green)
            .clickable {
                onItemClick.invoke(item)
            }
    ) {
        val thumbnail = remember {
            var result: ImageBitmap? = null
            scope.launch {
                try {
                    val retriever = MediaMetadataRetriever()
                    retriever.setDataSource(appContext, item.toUri())
                    val bitmap = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST)
                    result = bitmap?.asImageBitmap()
                } catch (e: Exception) {
                    Log.e("TAG", "DisplayThumbs: $e")
                }
            }
            result
        }


        if (item.isVideo()) {
            if (thumbnail != null)
                Image(
                    bitmap = thumbnail,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.5.dp),
                    contentDescription = ContentDescriptionThumbs,
                    contentScale = ContentScale.Crop
                )

        } else {
            val model = ImageRequest.Builder(appContext)
                .data(item)
                .build()
            Image(
                painter = rememberAsyncImagePainter(model),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.5.dp),
                contentDescription = ContentDescriptionThumbs,
                contentScale = ContentScale.Crop
            )

        }

        Image(
            painter = painterResource(id = if (item.isVideo()) R.drawable.ic_video else R.drawable.ic_image),
            contentDescription = ContentDescriptionThumbsIcon,
            modifier = Modifier
                .size(28.dp)
                .padding(iconPaddingValues)
                .align(Alignment.BottomEnd)
        )

    }
}


@Composable
fun DisplayThumbs(
    mediaLazyItems: LazyPagingItems<File>,
    index: Int,
    appContext: Context,
    onItemClick: (File) -> Unit
) {
    val item = mediaLazyItems.itemSnapshotList.items[index]
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(getHeightOfScreenInDp().div(5))
            .background(Color.Green)
            .clickable {
                onItemClick.invoke(item)
            }
    ) {


        if (item.isVideo()) {

//            val bitmap = ThumbnailUtils.createVideoThumbnail(
//                item.path,
//                MediaStore.Video.Thumbnails.MINI_KIND
//            )
//            val bitmapV2 = ThumbnailUtils.createVideoThumbnail(
//                item.path,
//                MediaStore.Video.Thumbnails.MICRO_KIND
//            )

            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(appContext, item.toUri())
            val thumbnail = retriever.getFrameAtTime(0)
            Image(
                painter = rememberAsyncImagePainter(model = thumbnail),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.5.dp),
                contentDescription = ContentDescriptionThumbs,
                contentScale = ContentScale.Crop
            )


        } else {
            val model = ImageRequest.Builder(appContext)
                .data(item)
                .build()
            Image(
                painter = rememberAsyncImagePainter(model),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.5.dp),
                contentDescription = ContentDescriptionThumbs,
                contentScale = ContentScale.Crop
            )

        }

        Image(
            painter = painterResource(id = if (item.isVideo()) R.drawable.ic_video else R.drawable.ic_image),
            contentDescription = ContentDescriptionThumbsIcon,
            modifier = Modifier
                .size(28.dp)
                .padding(iconPaddingValues)
                .align(Alignment.BottomEnd)
        )

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
