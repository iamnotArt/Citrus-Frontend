package com.example.citrusapp.Main.BottomNavFunctions

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.example.citrusapp.Main.Account.AccountScreen
import com.example.citrusapp.Main.Home.HomeScreen
import com.example.citrusapp.Main.Inbox.InboxScreen
import com.example.citrusapp.Main.LMS.LMSScreen
import com.example.citrusapp.Main.Network.NetworkScreen
import com.example.citrusapp.R
import kotlinx.coroutines.launch

@Composable
fun BottomNavScreen() {
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                modifier = Modifier.height(56.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    BottomNavItem(
                        "Home",
                        "home_light",
                        isSelected = selectedItem == 0,
                        onClick = { selectedItem = 0 }
                    )
                    BottomNavItem(
                        "LMS",
                        "article_icon_light",
                        isSelected = selectedItem == 1,
                        onClick = { selectedItem = 1 }
                    )
                    BottomNavItem(
                        "Network",
                        "newspaper_light",
                        isSelected = selectedItem == 2,
                        onClick = { selectedItem = 2 }
                    )
                    BottomNavItem(
                        "Inbox",
                        "inbox_light",
                        isSelected = selectedItem == 3,
                        onClick = { selectedItem = 3 }
                    )
                    BottomNavItem(
                        "Account",
                        null,
                        isSelected = selectedItem == 4,
                        onClick = { selectedItem = 4 }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when (selectedItem) {
                0 -> HomeScreen()
                1 -> LMSScreen()
                2 -> NetworkScreen()
                3 -> InboxScreen()
                4 -> AccountScreen()
            }
        }
    }
}

