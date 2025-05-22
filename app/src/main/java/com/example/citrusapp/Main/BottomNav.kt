package com.example.citrusapp.Main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    BottomNavItem("Home", selectedItem == 0) { selectedItem = 0 }
                    BottomNavItem("LMS", selectedItem == 1) { selectedItem = 1 }
                    BottomNavItem("Network", selectedItem == 2) { selectedItem = 2 }
                    BottomNavItem("Inbox", selectedItem == 3) { selectedItem = 3 }
                    BottomNavItem("Account", selectedItem == 4) { selectedItem = 4 }
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

@Composable
fun BottomNavItem(label: String, isSelected: Boolean, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(
            text = label,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
        )
    }
}
