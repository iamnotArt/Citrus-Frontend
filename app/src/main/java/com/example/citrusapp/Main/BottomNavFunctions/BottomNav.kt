package com.example.citrusapp.Main.BottomNavFunctions

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.citrusapp.Main.Account.AccountScreen
import com.example.citrusapp.Main.Home.HomeScreen
import com.example.citrusapp.Main.Inbox.InboxScreen
import com.example.citrusapp.Main.LMS.LMSScreen
import com.example.citrusapp.Main.Network.NetworkScreen

@Composable
fun BottomNavScreen() {
    var selectedItem by remember { mutableStateOf(0) }
    val isDarkTheme = isSystemInDarkTheme()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.background,
                tonalElevation = 8.dp,
                modifier = Modifier.height(60.dp)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp)
                            .alpha(0.6f)
                            .background(MaterialTheme.colorScheme.onSurface)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        BottomNavItem("Home", "home", selectedItem == 0, isDarkTheme) { selectedItem = 0 }
                        BottomNavItem("LMS", "article_icon", selectedItem == 1, isDarkTheme) { selectedItem = 1 }
                        BottomNavItem("Network", "newspaper", selectedItem == 2, isDarkTheme) { selectedItem = 2 }
                        BottomNavItem("Inbox", "inbox", selectedItem == 3, isDarkTheme) { selectedItem = 3 }
                        BottomNavItem("Account", null, selectedItem == 4, isDarkTheme) { selectedItem = 4 }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
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

