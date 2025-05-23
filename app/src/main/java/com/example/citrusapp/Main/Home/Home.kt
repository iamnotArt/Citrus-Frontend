package com.example.citrusapp.Main.Home

import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.citrusapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val listState = rememberLazyListState()
    val appBarHeight = 56.dp

    var lastScrollOffset by remember { mutableStateOf(0) }
    var isAppBarVisible by remember { mutableStateOf(true) }

    // Detect scroll direction
    LaunchedEffect(listState.firstVisibleItemScrollOffset, listState.firstVisibleItemIndex) {
        val currentOffset = listState.firstVisibleItemScrollOffset + listState.firstVisibleItemIndex * 1000
        isAppBarVisible = currentOffset < lastScrollOffset || listState.firstVisibleItemIndex == 0
        lastScrollOffset = currentOffset
    }

    val animatedAppBarHeight by animateDpAsState(
        targetValue = if (isAppBarVisible) appBarHeight else 0.dp,
        animationSpec = tween(durationMillis = 300),
        label = "AppBarHeight"
    )

    val contentTopPadding by animateDpAsState(
        targetValue = if (isAppBarVisible) appBarHeight else 0.dp,
        animationSpec = tween(durationMillis = 300),
        label = "ContentTopPadding"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Content
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = contentTopPadding,
                    start = 16.dp,
                    end = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(30) { index ->
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
        }

        // AppBar
        TopAppBar(
            title = {},
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.schoollogo),
                    contentDescription = "School Logo",
                    modifier = Modifier
                        .height(48.dp)
                        .padding(start = 8.dp)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(animatedAppBarHeight)
        )
    }
}
