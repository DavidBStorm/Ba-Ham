package com.task_baham.model

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.task_baham.util.getAllMediaFilesOnDevice
import kotlinx.coroutines.delay
import java.io.File

class MediaPagingSource(val app: Application) : PagingSource<Int, File>() {
    override fun getRefreshKey(state: PagingState<Int, File>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, File> {
        return try {
            delay(1000)
            Log.e("TAG", "load: in source", )
            val page = params.key ?: 1
            val response = getAllMediaFilesOnDevice(app)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}