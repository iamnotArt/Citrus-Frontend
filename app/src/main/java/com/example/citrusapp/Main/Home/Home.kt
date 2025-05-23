package com.example.citrusapp.Main.Home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.citrusapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val listState = rememberLazyListState()

    var previousScrollOffset by remember { mutableStateOf(0) }
    var isAppBarVisible by remember { mutableStateOf(true) }

    // Toggle visibility based on scroll direction
    LaunchedEffect(listState.firstVisibleItemScrollOffset) {
        val currentOffset = listState.firstVisibleItemScrollOffset
        isAppBarVisible = currentOffset < previousScrollOffset || listState.firstVisibleItemIndex == 0
        previousScrollOffset = currentOffset
    }

    val appBarHeight = 56.dp
    val offsetY by animateDpAsState(if (isAppBarVisible) 0.dp else -appBarHeight, label = "AppBarOffset")
    val contentTopPadding by animateDpAsState(if (isAppBarVisible) appBarHeight else 0.dp, label = "ContentTopPadding")

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
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
                    .height(appBarHeight)
                    .offset(y = offsetY),
                scrollBehavior = scrollBehavior
            )
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { padding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = contentTopPadding,
                    bottom = padding.calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(20) { index ->
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
    }
}
