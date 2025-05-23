package com.example.citrusapp.Main.BottomNavFunctions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.citrusapp.Main.Account.AccountScreen
import com.example.citrusapp.Main.Home.HomeScreen
import com.example.citrusapp.Main.Inbox.InboxScreen
import com.example.citrusapp.Main.LMS.LMSScreen
import com.example.citrusapp.Main.Network.NetworkScreen

@Composable
fun BottomNavScreen() {
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomAppBar(containerColor = MaterialTheme.colorScheme.surface) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        BottomNavItem(
                            "Home",
                            "home_light",
                            isSelected = selectedItem == 0,
                            onClick = { selectedItem = 0 }
                        )
                    }
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        BottomNavItem(
                            "LMS",
                            "article_icon_light",
                            isSelected = selectedItem == 1,
                            onClick = { selectedItem = 1 }
                        )
                    }
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        BottomNavItem(
                            "Network",
                            "newspaper_light",
                            isSelected = selectedItem == 2,
                            onClick = { selectedItem = 2 }
                        )
                    }
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        BottomNavItem(
                            "Inbox",
                            "inbox_light",
                            isSelected = selectedItem == 3,
                            onClick = { selectedItem = 3 }
                        )
                    }
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        BottomNavItem(
                            "Account",
                            null,
                            isSelected = selectedItem == 4,
                            onClick = { selectedItem = 4 }
                        )
                    }
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

