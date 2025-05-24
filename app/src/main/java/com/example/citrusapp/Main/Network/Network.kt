package com.example.citrusapp.Main.Network

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun NetworkScreen() {
    val listState = rememberLazyListState()
    val appBarHeight = 56.dp
    val isDarkTheme = isSystemInDarkTheme()

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
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.schoollogo),
                    contentDescription = "School Logo",
                    modifier = Modifier.height(34.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .clip(RoundedCornerShape(4.dp)) // same shape as OutlinedTextField
                        .border(
                            width = 1.dp,
                            brush = SolidColor(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)), // typical outline color
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 12.dp), // horizontal padding inside the box
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Search...",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        ),
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                // Clickable icon image
                Image(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Add Box Icon",
                    modifier = Modifier
                        .height(40.dp)
                        .clip(CircleShape)
                        .clickable {
                            //TODO: Add Post
                        },
                    colorFilter = ColorFilter.tint(
                        if (isDarkTheme) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
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
    }
}