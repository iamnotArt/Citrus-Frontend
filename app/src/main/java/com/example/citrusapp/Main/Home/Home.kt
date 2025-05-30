package com.example.citrusapp.Main.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

    HomeDrawer(
        drawerState = drawerState
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
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Featured",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )

                    HorizontalDivider()
                }

                ViewPagerSection()

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    Spacer(modifier = Modifier.height(2.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.LightGray)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(
                                text = "Shortcuts",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            HorizontalDivider(
                                color = Color.Black
                            )

                            // Add more content here if needed
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Recommended for You",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(16.dp))

                    // ✅ Add your Box here
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .height(350.dp) // adjust height as needed
                            .background(Color.LightGray), // sample background
                        contentAlignment = Alignment.Center
                    ) {
                        Text("This is a Box below the Recommended divider")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Discover",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(16.dp))

                    // ✅ Add your Box here
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .height(350.dp) // adjust height as needed
                            .background(Color.LightGray), // sample background
                        contentAlignment = Alignment.Center
                    ) {
                        Text("This is a Box below the Discover divider")
                    }

                    Spacer(modifier = Modifier.height(16.dp))



                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Courses",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )

                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(16.dp))

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
}
