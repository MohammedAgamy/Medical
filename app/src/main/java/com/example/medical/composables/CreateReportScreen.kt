package com.example.medical.composables

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.medical.R
import com.example.medical.info.createrepoInfo.CreateState
import com.example.medical.model.CreateReportViewModel
import com.example.medical.ui.theme.Black
import com.example.medical.ui.theme.GrayLight
import com.example.medical.ui.theme.PrimaryColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun CreateReportScreen(
    navHostController: NavHostController,
    createReportViewModel: CreateReportViewModel
) {

    val state by createReportViewModel.state.collectAsState()

    val reportName = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }


    val snackbarHostState = remember { SnackbarHostState() }



    when (state) {
        is CreateState.Idle -> {
            // Show login form
        }

        is CreateState.Loading -> {
            val infiniteTransition = rememberInfiniteTransition()
            val progress by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 3000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )

            LinearProgressIndicator(
                progress = progress,
                color = PrimaryColor,
                modifier = Modifier
                    .height(8.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
        }

        is CreateState.Success -> {
            Text((state as CreateState.Success).message, fontSize = 15.sp, color = Black)

        }

        is CreateState.Error -> {
            Text((state as CreateState.Error).error, color = Color.Red)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        // Back Button & Title
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navHostController.navigate("Reports") {
                    popUpTo("Reports") { inclusive = true }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Create Report",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Report Name Input
        OutlinedTextField(
            value = reportName.value,
            onValueChange = { reportName.value = it },
            label = { Text("Report Name") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Black,
                focusedBorderColor = PrimaryColor,
                cursorColor = PrimaryColor,
                unfocusedBorderColor = GrayLight
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Description Input
        OutlinedTextField(
            value = description.value,
            onValueChange = { description.value = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Black,
                focusedBorderColor = PrimaryColor,
                cursorColor = PrimaryColor,
                unfocusedBorderColor = GrayLight
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Upload Image Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(R.drawable.up), // Replace with your icon
                contentDescription = "Upload",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {

                    }
            )


        }

        Spacer(modifier = Modifier.height(80.dp))

        // Create Report Button

        Button(
            onClick = {
                if (reportName.value.isNotEmpty() || description.value.isNotEmpty()) {
                    createReportViewModel.createReport(reportName.value, description.value)
                    navHostController.popBackStack()
                    CoroutineScope(Dispatchers.Main).launch {
                        snackbarHostState.showSnackbar(
                            "Report Created",
                            null,
                            false,
                            SnackbarDuration.Short
                        )


                    }

                } else {
                    CoroutineScope(Dispatchers.Main).launch {
                        snackbarHostState.showSnackbar(
                            "Report Filed",
                            null,
                            false,
                            SnackbarDuration.Short
                        )

                    }
                }


            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(2.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF1ABC9C)),
            shape = RoundedCornerShape(6.dp)

        ) {
            Text(
                text = "Create Report",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }


}
