package com.task_baham.util

import android.Manifest
import android.app.Application
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.task_baham.ui.activities.MainActivity

fun requestPermission(
    activity: MainActivity,
    permissionGranted: () -> Unit,
    permissionDenied: () -> Unit
) {
    when {
        ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED -> {
            permissionGranted.invoke()
        }

        ActivityCompat.shouldShowRequestPermissionRationale(
            activity,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) -> {

          permissionDenied.invoke()
        }


        else ->  permissionDenied.invoke()
    }
}

@Composable
fun getWidthOfScreenInDp() = LocalConfiguration.current.screenWidthDp.dp
@Composable
fun getHeightOfScreenInDp() = LocalConfiguration.current.screenHeightDp.dp

 fun getAllMediaInStorage(application: Application): List<Uri> {
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
     Log.e("TAG", "getAllMediaInStorage: $allImages ")
    return allImages
}