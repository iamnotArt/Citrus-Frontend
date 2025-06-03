package com.example.citrusapp.Main.LMS

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.ui.unit.sp

@Composable
fun LMSScreen() {
    val listState = rememberLazyListState()
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Dashboard", "My Courses", "Available Courses")

    // App bar visibility state
    var isAppBarVisible by remember { mutableStateOf(true) }

    // Define different heights for each tab
    val dashboardAppBarHeight = 170.dp
    val coursesAppBarHeight = 68.dp

    // Calculate target height based on selected tab and visibility
    val targetHeight = when (selectedTabIndex) {
        0 -> if (isAppBarVisible) dashboardAppBarHeight else 0.dp
        else -> if (isAppBarVisible) coursesAppBarHeight else 0.dp
    }

    // Animate the height change
    val animatedAppBarHeight by animateDpAsState(
        targetValue = targetHeight,
        animationSpec = tween(durationMillis = 300),
        label = "AppBarHeightAnimation"
    )

    // Scroll listener for hide/show behavior
    LaunchedEffect(listState, selectedTabIndex) {
        var previousOffset = 0
        snapshotFlow {
            listState.firstVisibleItemIndex * 1000 + listState.firstVisibleItemScrollOffset
        }
            .distinctUntilChanged()
            .collectLatest { currentOffset ->
                val layoutInfo = listState.layoutInfo
                val visibleItems = layoutInfo.visibleItemsInfo
                val totalItems = layoutInfo.totalItemsCount

                val isAtBottom = visibleItems.lastOrNull()?.index == totalItems - 1
                val delta = currentOffset - previousOffset

                isAppBarVisible = when {
                    isAtBottom -> false
                    delta < -10 -> true // Scrolling up
                    delta > 10 -> false // Scrolling down
                    listState.firstVisibleItemIndex == 0 -> true
                    else -> isAppBarVisible
                }

                previousOffset = currentOffset
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // App Bar with animated height
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(animatedAppBarHeight)
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header content (only for Dashboard)
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

                // Tab row (always shown when app bar is visible)
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