package com.example.citrusapp.Main.Home

import AutoScrollPager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.citrusapp.Components.PagerIndicator
import com.example.citrusapp.R

@Composable
fun ViewPagerSection() {
    val pagerItems = listOf(
        R.drawable.education,
        R.drawable.engineering,
        R.drawable.graduate
    )

    val virtualPageCount = Int.MAX_VALUE
    val startPage = virtualPageCount / 2 - (virtualPageCount / 2) % pagerItems.size

    val pagerState = rememberPagerState(
        initialPage = startPage,
        pageCount = { virtualPageCount }
    )

    AutoScrollPager(pagerState = pagerState, delayMillis = 6000L)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val actualPage = page % pagerItems.size
                Card(
                    modifier = Modifier.fillMaxSize(),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RectangleShape
                ) {
                    Image(
                        painter = painterResource(id = pagerItems[actualPage]),
                        contentDescription = "Slide ${actualPage + 1}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.schoollogo),
                contentDescription = "School Logo",
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
                    .align(Alignment.TopStart)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        PagerIndicator(
            pageCount = pagerItems.size,
            currentPage = pagerState.currentPage % pagerItems.size,
            modifier = Modifier.padding(bottom = 12.dp)
        )
    }
}

