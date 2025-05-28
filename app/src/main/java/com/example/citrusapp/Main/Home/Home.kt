package com.example.citrusapp.Main.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.citrusapp.R
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val isDarkTheme = isSystemInDarkTheme()
    var isSettingsExpanded1 by remember { mutableStateOf(false) }
    var isSettingsExpanded2 by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Northwest Samar State University",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )

                HorizontalDivider()

                Text(
                    text = "About",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isSettingsExpanded1 = !isSettingsExpanded1 }
                        .padding(16.dp),
                    fontWeight = FontWeight.Medium
                )

                if (isSettingsExpanded1) {
                    Column(modifier = Modifier.padding(start = 32.dp)) {
                        Text(
                            text = "The University",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Account click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "Vision and Mission",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Notifications click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "NwSSU Official Seal",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Privacy click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "NwSSU General Mandate",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Privacy click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "NwSSU History",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Privacy click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "Board of Regents",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Privacy click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "Administration",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Privacy click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "International Affairs",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Privacy click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "Linkages",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Privacy click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "Sustainability Plan (SDG's)",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Privacy click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "Alumni Affairs",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Privacy click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "Gender and Development",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Privacy click */ }
                                .padding(vertical = 8.dp)
                        )
                    }
                }

                Text(
                    text = "Academe",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isSettingsExpanded2 = !isSettingsExpanded2 }
                        .padding(16.dp),
                    fontWeight = FontWeight.Medium
                )

                if (isSettingsExpanded2) {
                    Column(modifier = Modifier.padding(start = 32.dp)) {
                        Text(
                            text = "San Jorge Campus",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Account click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "Notifications",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Notifications click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "Privacy",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Privacy click */ }
                                .padding(vertical = 8.dp)
                        )
                    }
                }

                Text(
                    text = "Logout",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* Perform logout */ }
                        .padding(16.dp)
                )
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.schoollogo),
                        contentDescription = "School Logo",
                        modifier = Modifier.height(34.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Citrus NWSSU",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(id = R.drawable.ic_menu),
                        contentDescription = "Menu Icon",
                        modifier = Modifier
                            .height(34.dp)
                            .clip(CircleShape)
                            .clickable {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                        colorFilter = ColorFilter.tint(
                            if (isDarkTheme) MaterialTheme.colorScheme.onBackground
                            else MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(14) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Item #$index")
                        }
                    }
                }

                SwipableCardSection(
                    onCEAClick = { navController.navigate("cea") },
                    onEducClick = { navController.navigate("education") },
                    onManagementClick = { navController.navigate("management") },
                    onCCISClick = { navController.navigate("ccis") },
                    onCriminologyClick = { navController.navigate("criminology") },
                    onAgriClick = { navController.navigate("agriculture") },
                    onNursingClick = { navController.navigate("nursing") },
                    onGradClick = { navController.navigate("graduate") }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
