package com.task_baham.util

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.ui.unit.dp

const val PermissionDeniedText = "We need Permission to continue !!! \n Please click and retry"
const val GridItemSpan = 4
const val LoadingTxt = "Loading ... "
const val PickerTxtForDisplay = "Click for Display in full screen mode"
const val ContentDescriptionThumbs = "video thumbnail"
const val ContentDescriptionThumbsIcon = "Icon thumbnail"
const val NavigationKey = "Nav-key"


val iconPaddingValues = PaddingValues(6.dp, 4.dp)
val readImagePermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
    Manifest.permission.READ_MEDIA_IMAGES
else
    Manifest.permission.READ_EXTERNAL_STORAGE
val readVideoPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
    Manifest.permission.READ_MEDIA_VIDEO
else
    Manifest.permission.READ_EXTERNAL_STORAGE