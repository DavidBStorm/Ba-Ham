package com.task_baham.repository

import android.app.Application
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.task_baham.model.MediaPagingSource
import com.task_baham.util.PageSize

import javax.inject.Inject

class MediaRepository @Inject constructor(
    private val application: Application
) {
    fun getMedia() = Pager(
        config = PagingConfig(
            pageSize = PageSize
        ),
        pagingSourceFactory = {
            MediaPagingSource(application)
        }
    ).flow
}