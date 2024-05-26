package com.task_baham.ui.composable.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import java.io.File

@Composable
fun PlayerScreen(file: File? = null, shouldDisplayVideo: MutableState<Boolean>) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        VideoPlayer(file = file!!, shouldDisplayVideo = shouldDisplayVideo)
    }
}