package com.task_baham.ui.composable.universal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.task_baham.util.LoadingTxt
import com.task_baham.util.PermissionDeniedText
import com.task_baham.util.PickerTxtForDisplay
import com.task_baham.util.getHeightOfScreenInDp
import com.task_baham.util.getWidthOfScreenInDp

@Composable
fun DisplayPermissionNeed(onRetryClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .clickable {
                onRetryClicked.invoke()
            }
    ) {
        Text(
            text = PermissionDeniedText,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}

@Composable
fun DisplayLoading() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), textAlign = TextAlign.Center,
            text = LoadingTxt
        )

        CircularProgressIndicator(color = Color.Black,)
    }

}
@Composable
fun DisplayTextAboveList() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(getHeightOfScreenInDp().div(6))
            .background(Color.White),
    ) {

        Text(
            text = PickerTxtForDisplay,
            modifier = Modifier
                .align(Alignment.Center),
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun DisplayProgressBar(state: State<Boolean>) {
    if (state.value) {
        Dialog(
            onDismissRequest = { state.value },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(15.dp)
                    .width(getWidthOfScreenInDp().div(2))
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.Red
                )
                Spacer(modifier = Modifier.size(10.dp))
                androidx.compose.material.Text(
                    text = "Please wait ... ",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

            }


        }
    }
}