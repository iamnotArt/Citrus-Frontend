package com.example.citrusapp.Main.Home

import OrbitLoadingIndicator
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.citrusapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun SwipableCardSection() {
    val scope = rememberCoroutineScope()
    var isLoaded by remember { mutableStateOf(false) }

    // Define cards data first so we can access the count during loading
    val cardsWithData = remember {
        listOf(
            Triple("College of Engineering and Architecture", Color(0xFFEF5350), R.drawable.engineering),
            Triple("College of Education", Color(0xFFAB47BC), R.drawable.education),
            Triple("College of Management", Color(0xFF5C6BC0), R.drawable.management),
            Triple("College of Computing and Information Sciences", Color(0xFF26C6DA), R.drawable.computing),
            Triple("College of Criminal Justice and Sciences", Color(0xFF66BB6A), R.drawable.justice),
            Triple("College of Agriculture and Technology", Color(0xFFFFA726), R.drawable.agriculture),
            Triple("College of Nursing", Color(0xFFEC407A), R.drawable.nursing),
            Triple("Graduate School", Color(0xFF167A4E), R.drawable.graduate)
        )
    }
    val cardsCount = cardsWithData.size
    var currentIndex by remember { mutableStateOf(0) }

    // Load heavy resources after initial composition
    LaunchedEffect(Unit) {
        scope.launch {
            delay(1000) // Wait for initial frame
            isLoaded = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding(horizontal = 16.dp)
    ) {
        if (!isLoaded) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Blurred Image background
                    Image(
                        painter = painterResource(id = R.drawable.engineering),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(16.dp) // Apply blur to the image only
                    )

                    // Blurred Gradient Overlay (matches the design)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFFEF5350).copy(alpha = 0.7f),
                                        Color(0xFFEF5350).copy(alpha = 0.7f)
                                    ),
                                    startY = 0f,
                                    endY = Float.POSITIVE_INFINITY
                                )
                            )
                            .blur(16.dp) // Blur the gradient too
                    )

                    // Crisp Loading Indicator centered
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        OrbitLoadingIndicator(
                            dotColor = Color.White,
                            dotSize = 8f,
                            orbitRadius = 24f,
                            orbitDurationMillis = 1000,
                            dotCount = 4
                        )
                    }
                }
            }
        }
        else {
            // Loaded state with actual cards
            val cards = cardsWithData.map { it.first }
            val cardColors = cardsWithData.map { it.second }
            val cardImages = cardsWithData.map { it.third }

            val infiniteTransition = rememberInfiniteTransition(label = "Infinite >> Transition")
            val chevronAlpha by infiniteTransition.animateFloat(
                initialValue = 0.4f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ), label = ">> Animation"
            )

            var offsetX by remember { mutableStateOf(0f) }
            var offsetY by remember { mutableStateOf(0f) }
            var rotation by remember { mutableStateOf(0f) }
            var isAnimating by remember { mutableStateOf(false) }
            var shouldDismiss by remember { mutableStateOf(false) }
            var startOffsetX by remember { mutableStateOf(0f) }
            var startOffsetY by remember { mutableStateOf(0f) }
            var startRotation by remember { mutableStateOf(0f) }
            var cardWidth by remember { mutableStateOf(0) }
            var cardHeight by remember { mutableStateOf(0) }
            var nextCardScale by remember { mutableStateOf(0.9f) }
            var nextCardOffset by remember { mutableStateOf(30f) }
            var cardStackProgress by remember { mutableStateOf(0f) }

            // Animation states
            val throwOffsetX by animateFloatAsState(
                targetValue = if (shouldDismiss) (if (startOffsetX > 0) 2000f else -2000f) else 0f,
                animationSpec = tween(durationMillis = 250)
            )

            val throwOffsetY by animateFloatAsState(
                targetValue = if (shouldDismiss) startOffsetY * 3 else 0f,
                animationSpec = tween(durationMillis = 250)
            )

            val throwRotation by animateFloatAsState(
                targetValue = if (shouldDismiss) startRotation * 3 else 0f,
                animationSpec = tween(durationMillis = 250)
            )

            val returnOffsetX by animateFloatAsState(
                targetValue = if (isAnimating && !shouldDismiss) 0f else offsetX,
                animationSpec = tween(durationMillis = 250),
                finishedListener = {
                    if (isAnimating && !shouldDismiss) {
                        isAnimating = false
                    }
                }
            )

            val nextCardScaleAnim by animateFloatAsState(
                targetValue = if (shouldDismiss) 1f else nextCardScale,
                animationSpec = tween(durationMillis = 250)
            )

            val nextCardOffsetAnim by animateFloatAsState(
                targetValue = if (shouldDismiss) 0f else nextCardOffset,
                animationSpec = tween(durationMillis = 250)
            )

            val stackProgressAnim by animateFloatAsState(
                targetValue = if (shouldDismiss) 1f else 0f,
                animationSpec = tween(durationMillis = 250),
                finishedListener = {
                    cardStackProgress = 0f
                }
            )

            // Handle dismissal
            LaunchedEffect(shouldDismiss) {
                if (shouldDismiss) {
                    delay(250)
                    offsetX = 0f
                    offsetY = 0f
                    rotation = 0f
                    isAnimating = false
                    shouldDismiss = false
                    nextCardScale = 0.9f
                    nextCardOffset = 30f
                    currentIndex = (currentIndex + 1) % cards.size
                }
            }

            // Background cards fan
            val visibleCards = minOf(6, cards.size - 1)
            for (i in 0 until visibleCards) {
                val isNextCard = i == 0
                val isSecondCard = i == 1
                val progressFactor = if (isNextCard) stackProgressAnim else if (isSecondCard) stackProgressAnim * 0.8f else stackProgressAnim * 0.6f
                val cardIndex = (currentIndex + i + 1) % cards.size

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .zIndex((visibleCards - i).toFloat())
                        .graphicsLayer {
                            scaleX = if (isNextCard) nextCardScaleAnim else 0.9f - (0.03f * i)
                            scaleY = if (isNextCard) nextCardScaleAnim else 0.9f - (0.03f * i)
                            rotationZ = if (isNextCard) 0f else 2f * (i + 1)
                            translationY = if (isNextCard) {
                                nextCardOffsetAnim * (1 - nextCardScaleAnim)
                            } else {
                                (8.dp.toPx() * (i + 1)) - (8.dp.toPx() * progressFactor)
                            }
                            translationX = if (isNextCard) {
                                0f
                            } else {
                                (12.dp.toPx() * (i + 1)) - (12.dp.toPx() * progressFactor)
                            }
                            transformOrigin = TransformOrigin(0.5f, 0.5f)
                            if (!isNextCard) {
                                scaleX += 0.05f * progressFactor
                                scaleY += 0.05f * progressFactor
                            }
                        },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(
                        if (isNextCard) 6.dp else 4.dp - (1.dp * progressFactor)
                    )
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = cardImages[cardIndex]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .alpha(1f)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            cardColors[cardIndex].copy(alpha = 0.7f),
                                            cardColors[cardIndex].copy(alpha = 0.7f)
                                        ),
                                        startY = 0f,
                                        endY = Float.POSITIVE_INFINITY
                                    )
                                )
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    scaleX = 1f / (if (isNextCard) nextCardScaleAnim else 0.9f - (0.03f * i))
                                    scaleY = 1f / (if (isNextCard) nextCardScaleAnim else 0.9f - (0.03f * i))
                                }
                                .padding(32.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = cards[cardIndex],
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                textAlign = TextAlign.Start
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Row {
                                Text(
                                    text = "View more ",
                                    color = Color.White.copy(alpha = 0.8f),
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = ">>",
                                    color = Color.White.copy(alpha = chevronAlpha),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // Main swipable card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .zIndex(10f)
                    .onSizeChanged { size ->
                        cardWidth = size.width
                        cardHeight = size.height
                    }
                    .graphicsLayer {
                        if (isAnimating) {
                            if (shouldDismiss) {
                                translationX = startOffsetX + throwOffsetX
                                translationY = startOffsetY + throwOffsetY
                                rotationZ = startRotation + throwRotation
                                if (abs(throwOffsetX) > cardWidth / 4f) {
                                    nextCardScale = 1f
                                    nextCardOffset = 0f
                                    cardStackProgress = 1f
                                }
                            } else {
                                translationX = returnOffsetX
                                translationY = returnOffsetX * 0.1f
                                rotationZ = returnOffsetX / 20f
                            }
                        } else {
                            translationX = offsetX
                            translationY = offsetY
                            rotationZ = rotation
                        }
                        transformOrigin = TransformOrigin(0.5f, 0.5f)
                    }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                scope.launch {
                                    if (abs(offsetX) > cardWidth / 3f) {
                                        isAnimating = true
                                        shouldDismiss = true
                                        startOffsetX = offsetX
                                        startOffsetY = offsetY
                                        startRotation = rotation
                                        nextCardScale = 1f
                                        nextCardOffset = 0f
                                        cardStackProgress = 1f
                                    } else {
                                        isAnimating = true
                                        offsetX = 0f
                                        offsetY = 0f
                                        rotation = 0f
                                    }
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
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = cardImages[currentIndex]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(1f)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        cardColors[currentIndex].copy(alpha = 0.7f),
                                        cardColors[currentIndex].copy(alpha = 0.7f)
                                    ),
                                    startY = 0f,
                                    endY = Float.POSITIVE_INFINITY
                                )
                            )
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = cards[currentIndex],
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Start
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Row {
                            Text(
                                text = "View more ",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 14.sp
                            )
                            Text(
                                text = ">>",
                                color = Color.White.copy(alpha = chevronAlpha),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // Card counter - shown in both states
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = "${(currentIndex % cardsCount) + 1}/$cardsCount",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}