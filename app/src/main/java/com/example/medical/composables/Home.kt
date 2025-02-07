package com.example.medical.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medical.R
import com.example.medical.ui.theme.Black
import com.example.medical.ui.theme.PrimaryColor

@Composable
fun Home(navHostController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(40.dp))
        Header(navHostController)
        Spacer(modifier = Modifier.height(10.dp))
        DashboardScreen(navHostController)
    }
}

@Composable
fun Header(navHostController: NavHostController) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(1.dp)
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .weight(1f), shape = RoundedCornerShape(10.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.newimg),
                contentDescription = "Header",
                modifier = Modifier.clickable {
                    navHostController.navigate("Profile") {
                        popUpTo("Home") { inclusive = true }
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .weight(5f)
                .padding(1.dp)
                .padding(horizontal = 5.dp)
        ) {
            Text("Mohamed Agamy", color = Black, fontSize = 16.sp)
            Text("Manager", color = PrimaryColor, fontSize = 14.sp)
        }

        Icon(
            painter = painterResource(R.drawable.notification),
            contentDescription = "Notifications Icon",
            modifier = Modifier
                .size(27.dp)
                .weight(1f)
        )

    }
}


@Composable
fun DashboardScreen(navHostController: NavHostController) {
    val items = listOf(
        DashboardItem(
            "Calls",
            ImageVector.vectorResource(R.drawable.calls),
            Color(0xFF4A90E2), size = 200.dp
        ), // Blue
        DashboardItem(
            "Tasks",
            ImageVector.vectorResource(R.drawable.tasks),
            Color(0xFF7ED321), size = 150.dp
        ), // Green
        DashboardItem(
            "Reports",
            ImageVector.vectorResource(R.drawable.report),
            Color(0xFF9B51E0), size = 200.dp
        ), // Purple
        DashboardItem(
            "Attendance - Leaving",
            ImageVector.vectorResource(R.drawable.attend),
            Color(0xFF50E3C2), size = 150.dp
        ) // Cyan
    )

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2), // 2 columns
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),

        ) {
        items(items.size) { index ->
            DashboardCard(
                item = items[index],
                navHostController = navHostController
            ) // Pass navHostController here

        }
    }
}

// Data class for dashboard items
data class DashboardItem(
    val title: String,
    val icon: ImageVector,
    val color: Color,
    val size: Dp
)


@Composable
fun DashboardCard(item: DashboardItem, navHostController: NavHostController) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(item.size) // Fixed size for uniformity
            .clickable {
                // Handle click and navigate to the screen based on the card
                when (item.title) {
                    "Calls" -> navHostController.navigate("CallsScreen")
                    "Tasks" -> navHostController.navigate("Task")
                    "Reports" -> navHostController.navigate("Reports")
                    "Attendance - Leaving" -> navHostController.navigate("AttendanceScreen")
                }
            },
        shape = RoundedCornerShape(16.dp), // Rounded edges
        colors = CardDefaults.cardColors(item.color),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = item.icon,
                contentDescription = item.title,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }


}