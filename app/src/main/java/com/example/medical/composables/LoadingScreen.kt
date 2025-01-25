package com.example.medical.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medical.R
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import com.example.medical.ui.theme.PrimaryColor

@Composable
fun LoadingScreen(navController: NavHostController) {
// Navigate to the Login screen after a delay
    LaunchedEffect(Unit) {
        delay(3000L) // Simulate loading process
        navController.navigate("LogIn") {
            popUpTo("Loading") { inclusive = true }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),

        ) {
        BackgroundScreen()
        ProgressIndicator()
    }
}


@Composable
fun BackgroundScreen() {
    Image(
        painter = painterResource(R.drawable.backgroundtop),
        contentDescription = "BackGroundTop",
        modifier = Modifier.size(268.dp)
    )



    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End,
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.backgroundbuttom),
            contentDescription = "BackGroundTop",
            modifier = Modifier.size(268.dp),

            )
    }
}

@Composable
fun ProgressIndicator() {
    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.hospitellogo),
            contentDescription = "BackGroundTop",
            modifier = Modifier.size(180.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))

        LinearProgressIndicator(
            progress = progress,
            color = PrimaryColor,
            modifier = Modifier
                .height(8.dp)
                .clip(RoundedCornerShape(16.dp)),
        )


        Spacer(modifier = Modifier.height(10.dp))
        Text("Loading", fontSize = 16.sp, color = PrimaryColor)

    }

}

