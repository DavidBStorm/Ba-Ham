package com.task_baham.viewModel.home

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.task_baham.MyApplication
import com.task_baham.model.MediaPagingSource
import com.task_baham.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val application: MyApplication,
    private val mediaPagingSource: MediaRepository,
) : ViewModel() {
    private var mediaFlow: Flow<PagingData<File>> = mediaPagingSource.getMedia()


    fun getMedia(): Flow<PagingData<File>> = mediaPagingSource.getMedia().cachedIn(viewModelScope)

    fun getAppContext(): Context = application.applicationContext
    fun getApp() = application

}