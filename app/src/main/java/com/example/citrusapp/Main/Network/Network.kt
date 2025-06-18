package com.example.citrusapp.Main.Network

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.Main.Network.FindWorks.FindWorksTab
import com.example.citrusapp.Main.Network.Services.ServicesTab
import com.example.citrusapp.R

@Composable
fun NetworkScreen() {
    val tabTitles = listOf("Find Works", "Services")
    val pagerState = rememberPagerState { tabTitles.size }
    var selectedTabIndex by remember { mutableStateOf(0) }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    LaunchedEffect(selectedTabIndex) {
        if (selectedTabIndex != pagerState.currentPage) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.schoollogo),
                    contentDescription = "School Logo",
                    modifier = Modifier
                        .height(34.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = tabTitles[selectedTabIndex],
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp, // match HomeScreen title size
                )
            }
        }


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

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> FindWorksTab()
                1 -> ServicesTab()
            }
        }
    }
}