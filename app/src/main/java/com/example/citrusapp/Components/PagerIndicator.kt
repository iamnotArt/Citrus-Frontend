package com.example.citrusapp.Components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PagerIndicator(pageCount: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(pageCount) { index ->
            val targetColor = if (index == currentPage)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

            val animatedColor by animateColorAsState(targetValue = targetColor, label = "")

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(10.dp)
                    .background(color = animatedColor, shape = CircleShape)
            )
        }
    }
}
