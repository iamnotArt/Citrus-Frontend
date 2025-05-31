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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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
                            .height(450.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Top
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Shortcuts",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )

                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Info",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                        .clickable
                                        {
                                            //TODO: INFO CLICK
                                        }
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            HorizontalDivider()

                            Spacer(modifier = Modifier.height(24.dp))

                            Shortcuts()
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Row{
                        Text(
                            text = "Courses",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                        Text(
                            text = "(Swipable)",
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(start = 6.dp, top = 2.dp)
                        )

                    }


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


                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "Survey",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )

                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(16.dp))

                    //
                    Survey()
                }

            }
        }
    }
}
