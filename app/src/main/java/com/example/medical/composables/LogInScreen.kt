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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medical.R
import com.example.medical.info.logininf.LoginState
import com.example.medical.model.LoginViewModel
import com.example.medical.ui.theme.Black
import com.example.medical.ui.theme.GrayLight
import com.example.medical.ui.theme.PrimaryColor
import kotlinx.coroutines.DelicateCoroutinesApi

@Composable
fun LogInScreen(viewModel: LoginViewModel, navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize())
    {
        BackgroundLogIn()
        LoginFiled(viewModel, navController)
    }
}


@Composable
fun BackgroundLogIn() {

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
fun LoginFiled(viewModel: LoginViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
            .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 150.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Image(
            painter = painterResource(R.drawable.layer),
            contentDescription = "BackGroundTop",
            modifier = Modifier.size(105.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Welcome back !", color = PrimaryColor, fontSize = 26.sp)
        Text(text = "To Continue , Login Now", color = GrayLight, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(100.dp))
        OutLineTextFiled(viewModel, navController)
    }
}

@Composable
fun OutLineTextFiled(viewModel: LoginViewModel, navController: NavHostController) {

    val phone = remember { mutableStateOf("") }

    val password = remember { mutableStateOf("") }


    val state by viewModel.state.collectAsState()


    when (state) {
        is LoginState.Idle -> {
            // Show login form
        }

        is LoginState.Loading -> {
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

        is LoginState.Success -> {
            Text((state as LoginState.Success).message)
            navController.navigate("Home") {
                popUpTo("LogIn") { inclusive = true }
            }
        }

        is LoginState.Error -> {
            Text((state as LoginState.Error).error, color = Color.Red)
        }
    }

    Column(
        modifier = Modifier
            .padding(0.dp)
            .padding(horizontal = 20.dp)
    ) {
        OutlinedTextField(
            value = phone.value,
            onValueChange = { phone.value = it },
            label = { Text("Phone Number", color = GrayLight) },
            leadingIcon = { // Icon at the start of the text field
                Icon(
                    painter = painterResource(R.drawable.phoneas),
                    contentDescription = "Phone Icon", tint = PrimaryColor
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Black,
                focusedBorderColor = PrimaryColor,
                cursorColor = PrimaryColor,
                unfocusedBorderColor = GrayLight
            )
        )


        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password", color = GrayLight) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = { // Icon at the start of the text field
                Icon(
                    painter = painterResource(R.drawable.lock),
                    contentDescription = "Phone Icon", tint = PrimaryColor
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.pass),
                    contentDescription = "Phone Icon", tint = PrimaryColor
                )
            },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Black,
                focusedBorderColor = PrimaryColor,
                cursorColor = PrimaryColor,
                unfocusedBorderColor = GrayLight
            )
        )

        Text(
            "Forget Password ?",
            fontSize = 12.sp,
            color = Black,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                viewModel.login(phone.value, password.value) // Example credentials

            }, colors = ButtonColors(
                containerColor = PrimaryColor,
                contentColor = Color.White,
                disabledContainerColor = PrimaryColor,
                disabledContentColor = Color.White,
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("LogIn", color = Color.White)
        }

    }
}

@Composable
fun LogIn(viewModel: LoginViewModel) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is LoginState.Idle -> {
            // Show login form
        }

        is LoginState.Loading -> {
            CircularProgressIndicator()
        }

        is LoginState.Success -> {
            Text((state as LoginState.Success).message)
            // Navigate to home
        }

        is LoginState.Error -> {
            Text((state as LoginState.Error).error, color = Color.Red)
        }
    }

    when (state) {
        is LoginState.Idle -> {
            // Show login form
        }

        is LoginState.Loading -> {
            CircularProgressIndicator()
        }

        is LoginState.Success -> {
            Text((state as LoginState.Success).message)
            // Navigate to home
        }

        is LoginState.Error -> {
            Text((state as LoginState.Error).error, color = Color.Red)
        }
    }
}
