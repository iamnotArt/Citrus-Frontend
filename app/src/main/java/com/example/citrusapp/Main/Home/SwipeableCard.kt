package com.example.citrusapp.Main.Home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay
import kotlin.math.abs

@Composable
fun SwipableCardSection() {
    // Sample data for cards
    val cards = remember {
        listOf(
            "College of Engineering and Architecture",
            "College of Education",
            "College of Management",
            "College of Computing and Information Sciences",
            "College of Criminal Justice and Sciences",
            "College of Agriculture and Technology",
            "College of Nursing",
            "Graduate School",
        )
    }

    var currentIndex by remember { mutableStateOf(0) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var rotation by remember { mutableStateOf(0f) }
    var isAnimating by remember { mutableStateOf(false) }
    var shouldDismiss by remember { mutableStateOf(false) }
    var startOffsetX by remember { mutableStateOf(0f) }
    var startOffsetY by remember { mutableStateOf(0f) }
    var startRotation by remember { mutableStateOf(0f) }
    var cardWidth by remember { mutableStateOf(0) }

    // Animation for throwing the card away
    val throwOffsetX by animateFloatAsState(
        targetValue = if (shouldDismiss) (if (startOffsetX > 0) 2000f else -2000f) else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    val throwOffsetY by animateFloatAsState(
        targetValue = if (shouldDismiss) startOffsetY * 3 else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    val throwRotation by animateFloatAsState(
        targetValue = if (shouldDismiss) startRotation * 3 else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    // Animation for returning to center
    val returnOffsetX by animateFloatAsState(
        targetValue = if (isAnimating && !shouldDismiss) 0f else offsetX,
        animationSpec = tween(durationMillis = 300),
        finishedListener = {
            if (isAnimating && !shouldDismiss) {
                isAnimating = false
            }
        }
    )

    // Handle dismissal completion
    LaunchedEffect(shouldDismiss) {
        if (shouldDismiss) {
            delay(300) // Match animation duration
            currentIndex++
            offsetX = 0f
            offsetY = 0f
            rotation = 0f
            isAnimating = false
            shouldDismiss = false
        }
    }

    if (currentIndex < cards.size) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Increased height to accommodate the fan
                .padding(horizontal = 16.dp)
        ) {
            // Background cards fan (peeking from top)
            val visibleCards = minOf(5, cards.size - currentIndex - 1)
            for (i in 0 until visibleCards) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .zIndex((visibleCards - i).toFloat()) // Lower zIndex for cards behind
                        .offset(
                            x = (i + 1) * 12.dp, // Horizontal offset for fan effect
                            y = (i + 1) * 8.dp // Vertical offset for staircase
                        )
                        .graphicsLayer {
                            // Slight scale reduction for depth
                            scaleX = 1f - (0.03f * (i + 1))
                            scaleY = 1f - (0.03f * (i + 1))
                            // Slight rotation for fan effect
                            rotationZ = 2f * (i + 1)
                        },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cards[currentIndex + i + 1],
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(start = 32.dp) // Keep text visible
                        )
                    }
                }
            }

            // The main swipable card (on top of the fan)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .zIndex(10f) // Highest zIndex
                    .onSizeChanged { size ->
                        cardWidth = size.width
                    }
                    .graphicsLayer {
                        if (isAnimating) {
                            if (shouldDismiss) {
                                // During throw animation
                                translationX = startOffsetX + throwOffsetX
                                translationY = startOffsetY + throwOffsetY
                                rotationZ = startRotation + throwRotation
                            } else {
                                // During return animation
                                translationX = returnOffsetX
                                translationY = returnOffsetX * 0.1f
                                rotationZ = returnOffsetX / 20f
                            }
                        } else {
                            // During normal drag
                            translationX = offsetX
                            translationY = offsetY
                            rotationZ = rotation
                        }
                        transformOrigin = TransformOrigin(0f, 0f)
                    }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                if (abs(offsetX) > cardWidth / 3f) {
                                    // Swiped far enough - dismiss
                                    isAnimating = true
                                    shouldDismiss = true
                                    startOffsetX = offsetX
                                    startOffsetY = offsetY
                                    startRotation = rotation
                                } else {
                                    // Not far enough - return to center
                                    isAnimating = true
                                    offsetX = 0f
                                    offsetY = 0f
                                    rotation = 0f
                                }
                            },
                            onDrag = { change, dragAmount ->
                                if (!isAnimating) {
                                    change.consume()
                                    offsetX += dragAmount.x
                                    offsetY += dragAmount.y
                                    rotation = offsetX / 20f
                                }
                            }
                        )
                    },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = cards[currentIndex],
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Card counter
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = "${currentIndex + 1}/${cards.size}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No more cards to show", fontSize = 18.sp)
        }
    }
}