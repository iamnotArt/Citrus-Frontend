package com.example.citrusapp.Main.BottomNavFunctions

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.citrusapp.R
import kotlinx.coroutines.launch

@Composable
fun BottomNavItem(
    label: String,
    animationFile: String? = null,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            if (animationFile != null) getRawResId(animationFile) else 0
        )
    )
    val lottieAnimState = rememberLottieAnimatable()
    val coroutineScope = rememberCoroutineScope()

    val iconAlpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0.6f,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(70.dp)
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
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            if (animationFile != null) {
                LottieAnimation(
                    composition = composition,
                    progress = { lottieAnimState.progress },
                    modifier = Modifier
                        .size(24.dp)
                        .graphicsLayer(alpha = iconAlpha)
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = "Account Icon",
                    tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .graphicsLayer(alpha = iconAlpha)
                )
            }
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = iconAlpha),
                fontSize = 12.sp
            )
        }
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