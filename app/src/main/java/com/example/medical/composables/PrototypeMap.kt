package com.example.medical.composables

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medical.R
import com.example.medical.model.LoginViewModel
import com.example.medical.ui.HomeActivity
import com.example.medical.ui.RegisterActivity2
import com.example.medical.ui.theme.GrayLight
import com.example.medical.ui.theme.PrimaryColor

@Composable
fun PrototypeMap(loginViewModel: LoginViewModel) {
    Box(modifier = Modifier.fillMaxSize())
    {
        Background()
        MapClickable(loginViewModel)
    }
}


@Composable
fun Background() {

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
fun MapClickable(loginViewModel:LoginViewModel) {
    val context = LocalContext.current

    //view navPager to start for app
    val isLoggedIn by loginViewModel.isLoggedIn.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Text(
            text = "Prototype Map",
            fontSize = 20.sp,
            color = PrimaryColor,

            )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            OutlinedButton(
                onClick = {
                    if (isLoggedIn) {
                        val intent = Intent(context, HomeActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        val intent = Intent(context, RegisterActivity2::class.java)
                        context.startActivity(intent)
                    }

                },
                border = BorderStroke(1.dp, GrayLight),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Doctor", color = PrimaryColor)
            }
            OutlinedButton(
                onClick = {
                },
                border = BorderStroke(1.dp, GrayLight),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Receptionist", color = PrimaryColor)
            }
            OutlinedButton(
                onClick = {
                },
                border = BorderStroke(1.dp, GrayLight),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Nurse", color = PrimaryColor)
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            OutlinedButton(
                onClick = {
                },
                border = BorderStroke(1.dp, GrayLight),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Analysis Employee", color = PrimaryColor)
            }
            OutlinedButton(
                onClick = {
                },
                border = BorderStroke(1.dp, GrayLight),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Manger", color = PrimaryColor)
            }
            OutlinedButton(
                onClick = {
                },
                border = BorderStroke(1.dp, GrayLight),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("HR", color = PrimaryColor)
            }
        }
    }
}