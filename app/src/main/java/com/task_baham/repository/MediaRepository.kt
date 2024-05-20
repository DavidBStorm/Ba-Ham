package com.task_baham.repository

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.task_baham.model.MediaPagingSource

import javax.inject.Inject

class MediaRepository @Inject constructor(
    private val application: Application
) {
    fun getMedia() = Pager(
        config = PagingConfig(
            pageSize = 50
        ),
        pagingSourceFactory = {

            MediaPagingSource(application)
        }
    ).flow
}