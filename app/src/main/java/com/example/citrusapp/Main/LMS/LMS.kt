package com.example.citrusapp.Main.LMS

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LMSScreen() {
    val listState = rememberLazyListState()
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Dashboard", "My Courses", "Available Courses")

    // Define static target heights
    val dashboardAppBarHeight = 170.dp
    val coursesAppBarHeight = 68.dp

    // Animate height based on selected tab
    val targetHeight = if (selectedTabIndex == 0) dashboardAppBarHeight else coursesAppBarHeight
    val animatedAppBarHeight by animateDpAsState(
        targetValue = targetHeight,
        animationSpec = tween(durationMillis = 300),
        label = "AppBarHeightAnimation"
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Animated App Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(animatedAppBarHeight)
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header (only for Dashboard)
                if (selectedTabIndex == 0) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Art Lyndone Acuesta Hemplo",
                                style = MaterialTheme.typography.headlineMedium,
                                fontSize = 26.sp
                            )
                            Text(
                                text = "22-00489",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 18.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Tab row (always shown)
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = { Text(text = title) }
                        )
                    }
                }
            }
        }

        // Tab content
        when (selectedTabIndex) {
            0 -> DashboardTab(listState)
            1 -> MyCoursesTab(listState)
            2 -> AvailableCoursesTab(listState)
        }
    }
}
