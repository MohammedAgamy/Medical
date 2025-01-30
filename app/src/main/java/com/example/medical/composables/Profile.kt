package com.example.medical.composables

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medical.R
import com.example.medical.client.RetrofitInstance
import com.example.medical.data.profile_data.Profle
import com.example.medical.info.profileinfo.ProfileIntent
import com.example.medical.info.profileinfo.ProfileState
import com.example.medical.model.ProfileViewModel
import com.example.medical.ui.theme.PrimaryColor

@Composable
fun ProfileScreen(navHostController: NavHostController) {
    val viewModel = remember { ProfileViewModel(RetrofitInstance.apiService) }
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(ProfileIntent.FetchProfile(userId = 627))
    }



    when (state) {
        is ProfileState.Loading -> CircularProgressIndicator()
        is ProfileState.Success -> ProfileContent((state as ProfileState.Success).profile)
        is ProfileState.Error -> Text("Error: ${(state as ProfileState.Error).message}")
        else -> {}
    }

}


@Composable
fun ProfileContent(data: Profle) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1CC6AE)) // Background color
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Back Button and Title
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "My Profile",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            // Content Inside Box
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Profile Info Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.elevatedCardElevation(6.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Spacer(modifier = Modifier.height(150.dp))
                        ProfileItem(
                            ImageVector.vectorResource(R.drawable.medicalservices),
                            data.data.specialist
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        ProfileItem(ImageVector.vectorResource(R.drawable.male), data.data.gender)
                        Spacer(modifier = Modifier.height(10.dp))
                        ProfileItem(
                            ImageVector.vectorResource(R.drawable.calendartoday),
                            data.data.birthday
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        ProfileItem(
                            ImageVector.vectorResource(R.drawable.location),
                            data.data.address
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        ProfileItem(ImageVector.vectorResource(R.drawable.favorite), "Single")
                        Spacer(modifier = Modifier.height(10.dp))
                        ProfileItem(
                            ImageVector.vectorResource(R.drawable.email),
                            data.data.email
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        ProfileItem(
                            ImageVector.vectorResource(R.drawable.phoneas),
                            data.data.mobile
                        )
                    }
                }
                // Profile Image with Box (for Overlay Effect)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CircleShape), // Circular Image
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.newimg), // Replace with actual image
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                    // Name Text
                    Text(
                        text = data.data.first_name,
                        color = PrimaryColor,
                        fontSize = 28.sp
                    )
                }


            }

        }


    }
}


// Reusable Row Component for Profile Items
@Composable
fun ProfileItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, fontSize = 16.sp, color = Color.Black)
    }
}
