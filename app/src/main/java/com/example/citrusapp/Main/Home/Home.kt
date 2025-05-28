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

    var AboutExpanded by remember { mutableStateOf(false) }
    var AdministrationExpanded by remember { mutableStateOf(false) }
    var InternationalExpanded by remember { mutableStateOf(false) }
    var LinkagesExpanded by remember { mutableStateOf(false) }
    var SustainabilityExpanded by remember { mutableStateOf(false) }


    var AcademeExpanded by remember { mutableStateOf(false) }
    var OthersExpanded by remember { mutableStateOf(false) }

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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { AboutExpanded = !AboutExpanded }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "About",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f) // Pushes icon to the end
                    )

                    Icon(
                        painter = painterResource(
                            id = if (AboutExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                        ),
                        contentDescription = null
                    )
                }

                if (AboutExpanded) {
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
                        // Expandable Row for "Administration"
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { AdministrationExpanded = !AdministrationExpanded }
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Administration",
                                modifier = Modifier.weight(1f),
                                fontWeight = FontWeight.Bold
                            )

                            Icon(
                                painter = painterResource(
                                    id = if (AdministrationExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                ),
                                contentDescription = null
                            )
                        }

                        if (AdministrationExpanded) {
                            Column(modifier = Modifier.padding(start = 32.dp)) {
                                Text(
                                    text = "The University President",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "Key Officials",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle click */ }
                                        .padding(vertical = 8.dp)
                                )
                            }
                        }
                        // Expandable Row for "International Affairs"
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { InternationalExpanded = !InternationalExpanded }
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "International Affairs",
                                modifier = Modifier.weight(1f),
                                fontWeight = FontWeight.Bold
                            )

                            Icon(
                                painter = painterResource(
                                    id = if (InternationalExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                ),
                                contentDescription = null
                            )
                        }

                        if (InternationalExpanded) {
                            Column(modifier = Modifier.padding(start = 32.dp)) {
                                Text(
                                    text = "Office of the Director for International Affairs",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle click */ }
                                        .padding(vertical = 8.dp)
                                )
                            }
                        }

                        // Expandable Row for "Linkages"
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { LinkagesExpanded = !LinkagesExpanded }
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Linkages",
                                modifier = Modifier.weight(1f),
                                fontWeight = FontWeight.Bold
                            )

                            Icon(
                                painter = painterResource(
                                    id = if (LinkagesExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                ),
                                contentDescription = null
                            )
                        }

                        if (LinkagesExpanded) {
                            Column(modifier = Modifier.padding(start = 32.dp)) {
                                Text(
                                    text = "Regional Linkages",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "National Linkages",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "International Linkages",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle click */ }
                                        .padding(vertical = 8.dp)
                                )
                            }
                        }
                        // Expandable Row for "Sustainability Plan (SDGs)"
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { SustainabilityExpanded = !SustainabilityExpanded }
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Sustainability Plan (SDGs)",
                                modifier = Modifier.weight(1f),
                                fontWeight = FontWeight.Bold
                            )

                            Icon(
                                painter = painterResource(
                                    id = if (SustainabilityExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                ),
                                contentDescription = null
                            )
                        }

                        if (SustainabilityExpanded) {
                            Column(modifier = Modifier.padding(start = 32.dp)) {
                                Text(
                                    text = "Environmental Sustainability Plan",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "Energy and Water Conservation",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "Waste Management",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle click */ }
                                        .padding(vertical = 8.dp)
                                )
                            }
                        }
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { AcademeExpanded = !AcademeExpanded }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Academe",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f) // Pushes icon to the end
                    )

                    Icon(
                        painter = painterResource(
                            id = if (AcademeExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                        ),
                        contentDescription = null
                    )
                }

                if (AcademeExpanded) {
                    Column(modifier = Modifier.padding(start = 32.dp)) {
                        Text(
                            text = "San Jorge Campus",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Account click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "Colleges",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Notifications click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "School Calendar (SY: 2024 - 2025)",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Privacy click */ }
                                .padding(vertical = 8.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { OthersExpanded = !OthersExpanded }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Others",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f) // Pushes icon to the end
                    )

                    Icon(
                        painter = painterResource(
                            id = if (OthersExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                        ),
                        contentDescription = null
                    )
                }

                if (OthersExpanded) {
                    Column(modifier = Modifier.padding(start = 32.dp)) {
                        Text(
                            text = "Student Handbook",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Account click */ }
                                .padding(vertical = 8.dp)
                        )
                        Text(
                            text = "Registrar's Office",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* Handle Notifications click */ }
                                .padding(vertical = 8.dp)
                        )
                    }
                }
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
