// AppDrawer.kt
package com.example.citrusapp.Main.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.citrusapp.R
import com.example.citrusapp.ui.theme.blue_green
import com.example.citrusapp.ui.theme.calm_white
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeDrawer(
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavController,
    content: @Composable () -> Unit
) {
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
            ModalDrawerSheet(
                modifier = Modifier.background(blue_green),
                drawerContainerColor = blue_green,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.nwssutext),
                            contentDescription = "NwSSU Text Logo",
                            colorFilter = ColorFilter.tint(calm_white),
                            modifier = Modifier
                                .padding(16.dp)
                                .height(50.dp)
                                .fillMaxWidth()
                        )

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.drawerlogo1),
                                contentDescription = "NwSSU Text Logo 1",
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(end = 8.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.drawerlogo2),
                                contentDescription = "NwSSU Text Logo 2",
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(end = 8.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.drawerlogo3),
                                contentDescription = "NwSSU Text Logo 3",
                                modifier = Modifier
                                    .height(50.dp)
                            )
                        }

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
                                color = Color.White,
                                modifier = Modifier.weight(1f)
                            )

                            Icon(
                                painter = painterResource(
                                    id = if (AboutExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                ),
                                tint = calm_white,
                                contentDescription = null
                            )
                        }

                        if (AboutExpanded) {
                            Column(modifier = Modifier.padding(start = 32.dp)) {
                                Text(
                                    text = "The University",
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Account click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "Vision and Mission",
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Notifications click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "NwSSU Official Seal",
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Privacy click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "NwSSU General Mandate",
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Privacy click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "NwSSU History",
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Privacy click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "Board of Regents",
                                    color = Color.White,
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
                                        .padding(top = 8.dp, bottom = 8.dp, end = 18.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Administration",
                                        modifier = Modifier.weight(1f),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Icon(
                                        painter = painterResource(
                                            id = if (AdministrationExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                        ),
                                        contentDescription = null,
                                        tint = calm_white
                                    )
                                }

                                if (AdministrationExpanded) {
                                    Column(modifier = Modifier.padding(start = 32.dp)) {
                                        Text(
                                            text = "The University President",
                                            color = Color.White,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                        Text(
                                            text = "Key Officials",
                                            color = Color.White,
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
                                        .padding(top = 8.dp, bottom = 8.dp, end = 18.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "International Affairs",
                                        color = Color.White,
                                        modifier = Modifier.weight(1f),
                                        fontWeight = FontWeight.Bold
                                    )

                                    Icon(
                                        painter = painterResource(
                                            id = if (InternationalExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                        ),
                                        tint = calm_white,
                                        contentDescription = null
                                    )
                                }

                                if (InternationalExpanded) {
                                    Column(modifier = Modifier.padding(start = 32.dp)) {
                                        Text(
                                            text = "Office of the Director for International Affairs",
                                            color = Color.White,
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
                                        .padding(top = 8.dp, bottom = 8.dp, end = 18.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Linkages",
                                        color = Color.White,
                                        modifier = Modifier.weight(1f),
                                        fontWeight = FontWeight.Bold
                                    )

                                    Icon(
                                        painter = painterResource(
                                            id = if (LinkagesExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                        ),
                                        tint = calm_white,
                                        contentDescription = null
                                    )
                                }

                                if (LinkagesExpanded) {
                                    Column(modifier = Modifier.padding(start = 32.dp)) {
                                        Text(
                                            text = "Regional Linkages",
                                            color = Color.White,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                        Text(
                                            text = "National Linkages",
                                            color = Color.White,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                        Text(
                                            text = "International Linkages",
                                            color = Color.White,
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
                                        .padding(top = 8.dp, bottom = 8.dp, end = 18.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Sustainability Plan (SDGs)",
                                        color = Color.White,
                                        modifier = Modifier.weight(1f),
                                        fontWeight = FontWeight.Bold
                                    )

                                    Icon(
                                        painter = painterResource(
                                            id = if (SustainabilityExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                        ),
                                        tint = calm_white,
                                        contentDescription = null
                                    )
                                }

                                if (SustainabilityExpanded) {
                                    Column(modifier = Modifier.padding(start = 32.dp)) {
                                        Text(
                                            text = "Environmental Sustainability Plan",
                                            color = Color.White,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                        Text(
                                            text = "Energy and Water Conservation",
                                            color = Color.White,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                        Text(
                                            text = "Waste Management",
                                            color = Color.White,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { /* Handle click */ }
                                                .padding(vertical = 8.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "Alumni Affairs",
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Privacy click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "Gender and Development",
                                    color = Color.White,
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
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )

                            Icon(
                                painter = painterResource(
                                    id = if (AcademeExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                ),
                                tint = calm_white,
                                contentDescription = null
                            )
                        }

                        if (AcademeExpanded) {
                            Column(modifier = Modifier.padding(start = 32.dp)) {
                                Text(
                                    text = "San Jorge Campus",
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Account click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "Colleges",
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Notifications click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "School Calendar (SY: 2024 - 2025)",
                                    color = Color.White,
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
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )

                            Icon(
                                painter = painterResource(
                                    id = if (OthersExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                                ),
                                tint = calm_white,
                                contentDescription = null
                            )
                        }

                        if (OthersExpanded) {
                            Column(modifier = Modifier.padding(start = 32.dp)) {
                                Text(
                                    text = "Student Handbook",
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Account click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "Registrar's Office",
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Notifications click */ }
                                        .padding(vertical = 8.dp)
                                )
                                Text(
                                    text = "NWSSU Official Website",
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /* Handle Notifications click */ }
                                        .padding(vertical = 8.dp)
                                )
                            }
                        }
                    }

                    // Version text at the bottom, outside the scrollable content
                    Text(
                        text = "Version 1.0.0",
                        color = calm_white,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        content = content
    )
}