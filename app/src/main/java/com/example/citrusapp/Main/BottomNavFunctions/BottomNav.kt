package com.example.citrusapp.Main.BottomNavFunctions

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.example.citrusapp.Main.Account.AccountScreen
import com.example.citrusapp.Main.Home.HomeScreen
import com.example.citrusapp.Main.Inbox.InboxScreen
import com.example.citrusapp.Main.LMS.LMSScreen
import com.example.citrusapp.Main.Network.NetworkScreen
import com.example.citrusapp.R
import kotlinx.coroutines.launch

@Composable
fun BottomNavScreen() {
    var selectedItem by remember { mutableStateOf(0) }
    val isDarkTheme = isSystemInDarkTheme()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.background,
                tonalElevation = 8.dp,
                modifier = Modifier.height(60.dp)
            ) {
                Column {
                    // Top line indicator
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp)
                            .alpha(0.6f)
                            .background(MaterialTheme.colorScheme.onSurface)
                    )
                    // Navigation Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        BottomNavItem("Home", "home", selectedItem == 0, isDarkTheme) { selectedItem = 0 }
                        BottomNavItem("LMS", "article_icon", selectedItem == 1, isDarkTheme) { selectedItem = 1 }
                        BottomNavItem("Network", "newspaper", selectedItem == 2, isDarkTheme) { selectedItem = 2 }
                        BottomNavItem("Inbox", "inbox", selectedItem == 3, isDarkTheme) { selectedItem = 3 }
                        BottomNavItem("Account", null, selectedItem == 4, isDarkTheme) { selectedItem = 4 }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            when (selectedItem) {
                0 -> HomeScreen()
                1 -> LMSScreen()
                2 -> NetworkScreen()
                3 -> InboxScreen()
                4 -> AccountScreen()
            }
        }
    }
}

@Composable
fun BottomNavItem(
    label: String,
    animationFile: String?,
    isSelected: Boolean,
    isDarkTheme: Boolean,
    onClick: () -> Unit
) {
    // Inverted logic: use light version in dark mode and vice versa
    val fileName = if (animationFile != null) {
        "${animationFile}_${if (isDarkTheme) "light" else "dark"}"
    } else {
        null
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            if (fileName != null) getRawResId(fileName) else 0
        )
    )
    val lottieAnimState = rememberLottieAnimatable()
    val coroutineScope = rememberCoroutineScope()

    val iconAlpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0.5f,
        animationSpec = tween(durationMillis = 300)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(64.dp)
            .clip(CircleShape)
            .clickable(
                onClick = {
                    onClick()
                    if (animationFile != null) {
                        coroutineScope.launch {
                            lottieAnimState.animate(composition)
                        }
                    }
                }
            )
            .padding(vertical = 4.dp)
    ) {
        if (animationFile != null) {
            LottieAnimation(
                composition = composition,
                progress = { lottieAnimState.progress },
                modifier = Modifier
                    .size(20.dp)
                    .graphicsLayer(alpha = iconAlpha)
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_email),
                contentDescription = "Account Icon",
                modifier = Modifier
                    .size(20.dp)
                    .graphicsLayer(alpha = iconAlpha),
                tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
            )
        }
        Text(
            text = label,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = iconAlpha)
        )
    }
}

fun getRawResId(fileName: String): Int {
    return when (fileName) {
        "home_dark" -> R.raw.home_dark
        "home_light" -> R.raw.home_light
        "inbox_dark" -> R.raw.inbox_dark
        "inbox_light" -> R.raw.inbox_light
        "article_icon_dark" -> R.raw.article_icon_dark
        "article_icon_light" -> R.raw.article_icon_light
        "newspaper_dark" -> R.raw.newspaper_dark
        "newspaper_light" -> R.raw.newspaper_light
        else -> R.raw.home_dark
    }
}