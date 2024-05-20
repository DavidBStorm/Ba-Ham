package com.task_baham.util

import android.Manifest
import android.app.Application
import android.content.ContentUris
import android.content.Context
import android.content.CursorLoader
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.MergeCursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.task_baham.ui.activities.MainActivity
import java.io.File
import java.util.Locale


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


        else -> permissionDenied.invoke()
    }
}

@Composable
fun getWidthOfScreenInDp() = LocalConfiguration.current.screenWidthDp.dp

@Composable
fun getHeightOfScreenInDp() = LocalConfiguration.current.screenHeightDp.dp


fun getAllMediaFilesOnDevice(context: Application): List<File> {
    val files: ArrayList<File> = ArrayList()
    try {
        val columns = arrayOf(
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )
        val cursor = MergeCursor(
            arrayOf(
                context.contentResolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    columns,
                    null,
                    null,
                    "${MediaStore.Images.Media.DATE_ADDED} DESC"
                ),
                context.contentResolver.query(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    columns,
                    null,
                    null,
                    "${MediaStore.Video.Media.DATE_ADDED} DESC"
                ),
                context.contentResolver.query(
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                    columns,
                    null,
                    null,
                    "${MediaStore.Images.Media.DATE_ADDED} DESC"
                ),
                context.contentResolver.query(
                    MediaStore.Video.Media.INTERNAL_CONTENT_URI,
                    columns,
                    null,
                    null,
                    "${MediaStore.Video.Media.DATE_ADDED} DESC"
                )
            )
        )

        cursor.moveToFirst()
        files.clear()
        while (!cursor.isAfterLast) {
            var path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            val lastPoint = path.lastIndexOf(".")
            path = path.substring(0, lastPoint) + path.substring(lastPoint)
                .lowercase(Locale.getDefault())
            files.add(File(path))
            cursor.moveToNext()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return files.sortedByDescending { it.lastModified() }
}