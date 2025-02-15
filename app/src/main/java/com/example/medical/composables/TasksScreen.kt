package com.example.medical.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medical.R
import com.example.medical.data.taks_data.Data

import com.example.medical.info.tasks_info.TasksIntent
import com.example.medical.info.tasks_info.TasksState
import com.example.medical.model.TasksViewModel
import com.example.medical.repo.task_imp.TasksImpl
import com.example.medical.ui.theme.Black
import com.example.medical.ui.theme.GrayLight
import com.example.medical.ui.theme.PrimaryColor
import java.util.Calendar
import java.util.Date
import kotlin.String as String

@Composable
fun TasksScreen(navHostController: NavHostController) {


    // check this imp
    val viewModel = remember { TasksViewModel(TasksImpl()) }
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("AllTasks", "LaunchedEffect triggered") // Log when LaunchedEffect is triggered
        Log.d("AllTasks", state.toString()) // Log when LaunchedEffect is triggered
        viewModel.handleIntent(TasksIntent.LoadTasks)
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        // Back Button and Title
        Spacer(modifier = Modifier.height(40.dp))
        ShowHeaderTask(navHostController)
        Spacer(modifier = Modifier.width(8.dp))
        DatePickerTextFieldTask(navHostController)

        // Add a Spacer to ensure the LazyColumn is visible
        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn with weight modifier

        when (state) {
            is TasksState.Loading -> {
                // Log the loading state
                Log.d("TasksScreen", "Loading Tasks...")
                CircularProgressIndicator(modifier = Modifier.size(40.dp))
            }

            is TasksState.Success -> {
                val tasks = (state as TasksState.Success).reports
                Log.d("API Response", "Tasks: $tasks") // Log the reports list
                Log.d("API not", "Tasks: $tasks") // Log the reports list
                ShowList(tasks = tasks)
            }

            is TasksState.Error -> {
                // Log the error message
                Log.e("TasksScreen", "Error: ${(state as TasksState.Error).message}")
                Text(text = "Error: ${(state as TasksState.Error).message}")
            }

            TasksState.Idle -> {

            }
        }
    }


}

@Composable
fun ShowList(tasks: List<Data>) {

    Log.d("TasksScreen", "Tasks loaded: ${tasks.size}") // Log the size of reports

    if (tasks.isEmpty()) {
        // If the reports list is empty, show a message
        Text("No Tasks available", modifier = Modifier.fillMaxSize())
    } else {
        // Display the reports in LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(tasks) { task ->
                Log.d("Tasks Item", "Tasks: $task") // Log each report item
                TasksItem(tasks = task)
            }
        }
    }
}

@Composable
fun ShowHeaderTask(navHostController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navHostController.popBackStack()
                }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Tasks",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerTextFieldTask(navHostController: NavHostController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        var showDatePicker by remember { mutableStateOf(false) }
        var selectedDate by remember { mutableStateOf("") }

        var day by remember { mutableStateOf(0) }
        var month by remember { mutableStateOf(0) }
        var year by remember { mutableStateOf(0) }

        if (showDatePicker) {
            val datePickerState = rememberDatePickerState()

            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val selectedMillis = datePickerState.selectedDateMillis
                            if (selectedMillis != null) {
                                val calendar = Calendar.getInstance()
                                calendar.time = Date(selectedMillis)

                                day = calendar.get(Calendar.DAY_OF_MONTH)
                                month = calendar.get(Calendar.MONTH) + 1
                                year = calendar.get(Calendar.YEAR)

                                selectedDate = "$day.$month.$year"
                            }
                            showDatePicker = false
                        }
                    ) {
                        Text("OK")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        OutlinedTextField(
            value = selectedDate,
            onValueChange = { selectedDate = it },
            label = { Text("2.2.2025") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .clickable {
                    showDatePicker = true
                },
            readOnly = true,
            trailingIcon = {
                Image(
                    painter = painterResource(R.drawable.clander),
                    contentDescription = "Select Date",
                    modifier = Modifier
                        .weight(0.5f)
                        .size(44.dp)
                        .clickable { showDatePicker = true },

                    )

            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Black,
                focusedBorderColor = PrimaryColor,
                cursorColor = PrimaryColor,
                unfocusedBorderColor = GrayLight
            )
        )



        Image(
            painter = painterResource(R.drawable.buttonadd),
            contentDescription = "add new repo",
            modifier = Modifier
                .padding(1.dp)
                .padding(top = 6.dp)
                .weight(0.5f)
                .size(44.dp)
                .clickable {
                    navHostController.navigate("CreateTask") {
                        popUpTo("Tasks") { inclusive = true }

                    }
                },

            )


    }
}

@Composable
fun TasksItem(tasks: Data) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,

            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(12.dp))
            // Report Info
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.mark),
                        contentDescription = "doc",
                        tint = Color.Unspecified, // This prevents automatic tinting
                        modifier = Modifier
                            .size(28.dp) // Set a fixed size
                            .padding(end = 8.dp) // Add space between icon & text
                    )
                    Text(
                        text = tasks.task_name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,

                        )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.cal),
                        contentDescription = "cal",
                        tint = Color.Unspecified, // This prevents automatic tinting
                        modifier = Modifier

                            .size(28.dp) // Set a fixed size
                            .padding(end = 8.dp) // Add space between icon & text
                    )
                    Text(
                        text = tasks.created_at,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,

                        )
                }

            }

            // Status Badge
            TaskStatusBadge1(tasks.status)
        }
    }
}


@Composable
fun TaskStatusBadge1(status: String) {

    val (bgColor, textColor) = when (status) {
        "Finished" -> Pair(Color(0xFFBDD9EE), Color(0xFF1E88E5)) // Light blue & blue
        "Process" -> Pair(Color(0xFFEAC8CF), Color(0xFFD32F2F)) // Light red & red
        "pending" -> Pair(Color(0xFF807C7C), Color(0xFF000000)) // Light red & red
        "done" -> Pair(Color(0xFFCDE5B4), Color(0xFF59BE5D)) // Light red & red
        else -> Pair(Color.LightGray, Color.DarkGray)
    }

    Box(
        modifier = Modifier
            .background(bgColor, shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(text = status, color = textColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}