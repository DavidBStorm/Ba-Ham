package com.task_baham.model

import android.app.Application
import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.task_baham.util.getAllMediaInStorage
import kotlinx.coroutines.delay

class MediaPagingSource(val app: Application) : PagingSource<Int, Uri>() {
    override fun getRefreshKey(state: PagingState<Int, Uri>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Uri> {
        return try {
            delay(1000)
            val page = params.key ?: 1
            val response = getAllMediaInStorage(app)
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