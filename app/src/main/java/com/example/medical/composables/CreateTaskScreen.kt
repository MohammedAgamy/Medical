package com.example.medical.composables

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.medical.R
import com.example.medical.info.CreateTaskInfo.CreateTaskState
import com.example.medical.model.CreateTaskViewModel
import com.example.medical.ui.theme.Black
import com.example.medical.ui.theme.GrayLight
import com.example.medical.ui.theme.PrimaryColor



@Composable
fun CreateTaskScreen(
    navHostController: NavHostController,
    createTaskViewModel: CreateTaskViewModel
) {

    val state by createTaskViewModel.state.collectAsState()

    val description = remember { mutableStateOf("") }
    val taskName = remember { mutableStateOf("") }
    val userId = remember { mutableStateOf(3) }







    when (state) {
        is CreateTaskState.Idle -> {
            // Show login form
        }

        is CreateTaskState.Loading -> {
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

        is CreateTaskState.Success -> {
            Text((state as CreateTaskState.Success).message, fontSize = 15.sp, color = Black)

        }

        is CreateTaskState.Error -> {
            Text((state as CreateTaskState.Error).error, color = Color.Red)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(50.dp))
        HeaderTask(navHostController)
        Spacer(modifier = Modifier.height(20.dp))
        TextFledInput(
            description,
            taskName,
            userId,
            createTaskViewModel,
            navHostController
        )




    }

}


@Composable
fun TextFledInput(
    description: MutableState<String>,
    taskName: MutableState<String>,
    userId: MutableState<Int>,
    taskViewModel: CreateTaskViewModel,
    navHostController: NavHostController
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedEmployee by remember { mutableStateOf("Select Employee") }
    val employeeRoles = listOf("Manager", "Doctor", "Receptionist", "HR", "Nurse")

    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }




    // Launcher for gallery intent
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUri = result.data?.data
            Log.d("iamge", imageUri.toString())

        }
    }

    // Launcher for permission request
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission granted, open gallery
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        } else {
            // Permission denied, show a message
            // You can use a Snackbar or Toast here
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
    ) {
        // Task Name Input
        OutlinedTextField(
            value = taskName.value,
            onValueChange = { taskName.value = it },
            label = { Text("Task Name") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Black,
                focusedBorderColor = PrimaryColor,
                cursorColor = PrimaryColor,
                unfocusedBorderColor = GrayLight
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Select Employee (Dropdown)
        Box(modifier = Modifier.clickable { expanded = true }) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded = true
                        Log.d("TAG", expanded.toString())
                    },
                value = selectedEmployee,
                onValueChange = { selectedEmployee = it },
                label = { Text("Select Employee") },
                trailingIcon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Select Employee",
                        modifier = Modifier.clickable { expanded = true }
                    )
                },

                )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                employeeRoles.forEach { role ->
                    DropdownMenuItem(
                        text = { Text(role) },
                        onClick = {
                            selectedEmployee = role
                            expanded = false
                        }
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(12.dp))

        // Description
        OutlinedTextField(
            value = description.value,
            onValueChange = { description.value = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Black,
                focusedBorderColor = PrimaryColor,
                cursorColor = PrimaryColor,
                unfocusedBorderColor = GrayLight
            )
        )

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
                        // Check permission
                        val permission =
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                                Manifest.permission.READ_MEDIA_IMAGES
                            } else {
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            }

                        if (ContextCompat.checkSelfPermission(
                                context,
                                permission
                            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
                        ) {
                            // Permission already granted, open gallery
                            val intent = Intent(Intent.ACTION_PICK)
                            intent.type = "image/*"
                            galleryLauncher.launch(intent)
                        } else {
                            // Request permission
                            permissionLauncher.launch(permission)
                        }


                    }
            )


        }
        Button(
            onClick = {
                if (taskName.value.isNotEmpty() || description.value.isNotEmpty()) {
                    taskViewModel.createReport(
                        userId.value,
                        taskName.value,
                        imageUri!!,
                        description.value,

                        )
                    navHostController.popBackStack()
                    /*CoroutineScope(Dispatchers.Main).launch {
                        snackbarHostState.showSnackbar(
                            "Report Created",
                            null,
                            false,
                            SnackbarDuration.Short
                        )*/

                }


                /* CoroutineScope(Dispatchers.Main).launch {
                     snackbarHostState.showSnackbar(
                         "Report Filed",
                         null,
                         false,
                         SnackbarDuration.Short
                     )

                 }*/


            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(2.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF1ABC9C)),
            shape = RoundedCornerShape(6.dp)

        ) {
            Text(
                text = "Create Task",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun HeaderTask(navHostController: NavHostController) {
    // Top Bar
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navHostController.navigate("Task") {
                        popUpTo("Task") { inclusive = true }

                    }
                }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Create Task",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

    }

}


