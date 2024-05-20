package com.task_baham.viewModel.home

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task_baham.MyApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val application: MyApplication,
) : ViewModel() {

    val showProgressBar: StateFlow<Boolean> get() = _showProgressBar
    private val _showProgressBar = MutableStateFlow(true)

    private val _getMedia = MutableStateFlow(listOf<Uri>())
    val getMedia: StateFlow<List<Uri>> get() = _getMedia


    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("TAG", "error main: ${throwable.message}")
    }

    private fun getAllMediaInStorage(): List<Uri> {
        val allImages = mutableListOf<Uri>()

        val imageProjection = arrayOf(
            MediaStore.Images.Media._ID
        )

        val imageSortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        val cursor = application.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            imageProjection,
            null,
            null,
            imageSortOrder
        )

        cursor.use {

            if (cursor != null) {
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                while (cursor.moveToNext()) {
                    allImages.add(
                        ContentUris.withAppendedId(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            cursor.getLong(idColumn)
                        )
                    )
                }
            } else {
                Log.d("AddViewModel", "Cursor is null!")
            }
        }
        return allImages
    }

    fun getAllMedia() {
        job = viewModelScope.launch(exceptionHandler) {
            _getMedia.value = getAllMediaInStorage()
            delay(1000)
            _showProgressBar.value = false
        }
    }

    fun getAppContext(): Context = application.applicationContext
    fun getApp() = application

}