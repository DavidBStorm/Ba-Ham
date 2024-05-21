package com.task_baham.ui.composable.image

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.task.baham.R
import com.task_baham.util.ContentDescriptionThumbs
import com.task_baham.util.NavigationKey
import java.io.File
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

@Composable
fun ImageScreen(file: File?= null) {
    val backButtonPressed = remember { mutableStateOf(false) }

    val endRand = remember {
        Random.nextFloat()
    }
    val startRand = remember {
        Random.nextFloat()
    }
    val randImage = remember {
        ThreadLocalRandom.current().nextInt(1, 4)
    }
    ConstraintLayout(modifier = Modifier.background(Color.LightGray)) {
        val (gif, image) = createRefs()


        AsyncImage(
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxSize()
                .padding(0.5.dp),
            model = file,
            contentDescription = ContentDescriptionThumbs,
            contentScale = ContentScale.Inside
        )


        val guidelineStart = createGuidelineFromStart(startRand)
        val guidelineTop = createGuidelineFromTop(endRand)

        val drawableRes = when (randImage) {
            1 -> R.drawable.ic_glasses1
            2 -> R.drawable.ic_glasses2
            3 -> R.drawable.ic_glasses3
            4 -> R.drawable.ic_glasses4
            else -> R.drawable.ic_glasses1
        }
        if (!backButtonPressed.value)
        Image(
            painter = painterResource(id = drawableRes),
            contentDescription = ContentDescriptionThumbs,
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .constrainAs(gif) {

                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    linkTo(parent.top, guidelineTop)
                    linkTo(parent.start, guidelineStart)


                }
        )

    }

}