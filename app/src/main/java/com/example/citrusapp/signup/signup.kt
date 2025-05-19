package com.example.citrusapp.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.citrusapp.Components.PagerIndicator
import com.example.citrusapp.R
import com.example.citrusapp.signup.slides.SlideOne
import com.example.citrusapp.signup.slides.SlideThree
import com.example.citrusapp.signup.slides.SlideTwo
import kotlinx.coroutines.launch

@Composable
fun SignupScreen(loginClick: () -> Unit) {
    val pageCount = 3
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pageCount })
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.shapes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )

        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { loginClick() },
                    modifier = Modifier
                        .height(46.dp)
                        .padding(start = 16.dp, top = 18.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back"
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.citruslogo),
                    contentDescription = "Citrus Logo",
                    modifier = Modifier
                        .height(44.dp)
                        .padding(top = 7.5.dp, end = 58.dp)
                )

                Spacer(modifier = Modifier.weight(1f))
            }

            // Pager indicator
            PagerIndicator(
                pageCount = pageCount,
                currentPage = pagerState.currentPage,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Pager for slides
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) { page ->
                when (page) {
                    0 -> SlideOne()
                    1 -> SlideTwo()
                    2 -> SlideThree()
                }
            }
        }
    }
}
