package com.example.citrusapp.onboardingScreen

import AutoScrollPager
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.Components.PagerIndicator
import com.example.citrusapp.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(modifier: Modifier = Modifier) {
    val realPageCount = 3
    val fakePageCount = 1000
    val startIndex = (fakePageCount / 2) - ((fakePageCount / 2) % realPageCount)
    val isDarkTheme = isSystemInDarkTheme()

    val pagerState = rememberPagerState(
        initialPage = startIndex,
        pageCount = { fakePageCount }
    )

    AutoScrollPager(pagerState)

    val pages = listOf(
        Pair("Welcome", "Track your expenses with ease."),
        Pair("Analyze", "View insights and breakdowns."),
        Pair("Control", "Stay on top of your budget.")
    )

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.citruslogo),
                contentDescription = "Citrus Logo",
                modifier = Modifier
                    .padding(top = 8.dp, start = 4.dp)
                    .height(36.dp),
                colorFilter = ColorFilter.tint(
                    if (isDarkTheme) Color.White else Color.Black
                )
            )

            Spacer(modifier = Modifier.height(12.dp))
            PagerIndicator(
                pageCount = realPageCount,
                currentPage = pagerState.currentPage % realPageCount
            )
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { index ->
            val realIndex = index % realPageCount
            val (title, description) = pages[realIndex]
            OnboardingPage(title = title, description = description)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Already have an account?")
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    // TODO: Navigate to Login
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Don't have an account?",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Text(
                text = "Signup",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 40.dp)
                    .clickable(
                        onClick = {
                            // TODO: Handle signup click (e.g. navigate to Signup screen)
                        },
                        role = Role.Button
                    )
                    .indication(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true)
                    )
            )

        }

    }
}

@Composable
fun OnboardingPage(title: String, description: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

