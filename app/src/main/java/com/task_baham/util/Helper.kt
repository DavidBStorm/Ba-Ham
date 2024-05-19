package com.task_baham.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
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