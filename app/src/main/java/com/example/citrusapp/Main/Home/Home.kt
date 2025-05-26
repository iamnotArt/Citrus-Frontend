package com.example.citrusapp.Main.Home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.example.citrusapp.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun HomeScreen() {
    val listState = rememberLazyListState()
    val appBarHeight = 56.dp
    val isDarkTheme = isSystemInDarkTheme()

    val scrollState = rememberScrollState()

    var isAppBarVisible by remember { mutableStateOf(true) }

    LaunchedEffect(listState) {
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
                    delta < -10 -> true
                    delta > 10 -> false
                    listState.firstVisibleItemIndex == 0 -> true
                    else -> isAppBarVisible
                }

                previousOffset = currentOffset
            }
    }

    val animatedAppBarHeight by animateDpAsState(
        targetValue = if (isAppBarVisible) appBarHeight else 0.dp,
        animationSpec = tween(durationMillis = 300),
        label = "AppBarHeight"
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(animatedAppBarHeight)
                .padding(horizontal = 14.dp, vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {

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

                // Flexible space pushes content after this to the end
                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "School Logo",
                    modifier = Modifier
                        .height(34.dp)
                        .clip(CircleShape)
                        .clickable(
                            onClick = {
                                //TODO: Open Drawer
                            }
                        ),
                    colorFilter = ColorFilter.tint(
                        if (isDarkTheme) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onBackground
                    )
                )

            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState) // ✅ Make it scrollable
                .padding(horizontal = 16.dp),
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

            SwipableCardSection()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

