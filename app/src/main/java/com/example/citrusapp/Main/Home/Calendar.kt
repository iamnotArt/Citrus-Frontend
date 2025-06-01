package com.example.citrusapp.Main.Home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@Composable
fun Calendar() {
    var selectedMonth by remember { mutableStateOf(YearMonth.now()) }
    var direction by remember { mutableStateOf(0) } // -1 for left, 1 for right, 0 for initial

    val today = LocalDate.now()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Column {
            // Header with navigation
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "<",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .clickable {
                            direction = -1
                            selectedMonth = selectedMonth.minusMonths(1)
                        }
                        .padding(8.dp)
                )

                // Animated month/year display
                AnimatedContent(
                    targetState = selectedMonth,
                    transitionSpec = {
                        val slideDirection = if (direction > 0) 1 else -1

                        if (targetState > initialState) {
                            // Enter from right + fade in, exit to left + fade out
                            (slideInHorizontally { width -> slideDirection * width } + fadeIn())
                                .togetherWith(slideOutHorizontally { width -> -slideDirection * width } + fadeOut())
                        } else {
                            // Enter from left + fade in, exit to right + fade out
                            (slideInHorizontally { width -> -slideDirection * width } + fadeIn())
                                .togetherWith(slideOutHorizontally { width -> slideDirection * width } + fadeOut())
                        }.using(
                            SizeTransform(clip = false)
                        )
                    },
                    label = "MonthYearAnimation"
                ) { targetMonth ->
                    Text(
                        text = "${targetMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${targetMonth.year}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Text(
                    text = ">",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .clickable {
                            direction = 1
                            selectedMonth = selectedMonth.plusMonths(1)
                        }
                        .padding(8.dp)
                )
            }

            // Day headers
            Row(modifier = Modifier.fillMaxWidth()) {
                listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat").forEach { day ->
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day,
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Animated calendar days
            AnimatedContent(
                targetState = selectedMonth,
                transitionSpec = {
                    val slideDirection = if (direction > 0) 1 else -1

                    if (targetState > initialState) {
                        // Enter from right + fade in, exit to left + fade out
                        (slideInHorizontally { width -> slideDirection * width } + fadeIn())
                            .togetherWith(slideOutHorizontally { width -> -slideDirection * width } + fadeOut())
                    } else {
                        // Enter from left + fade in, exit to right + fade out
                        (slideInHorizontally { width -> -slideDirection * width } + fadeIn())
                            .togetherWith(slideOutHorizontally { width -> slideDirection * width } + fadeOut())
                    }.using(
                        SizeTransform(clip = false)
                    )
                },
                label = "CalendarAnimation"
            ) { targetMonth ->
                CalendarGrid(targetMonth, today)
            }
        }
    }
}

@Composable
private fun CalendarGrid(month: YearMonth, today: LocalDate) {
    val daysInMonth = month.lengthOfMonth()
    val firstDayOfWeek = month.atDay(1).dayOfWeek.value % 7
    var day = 1

    Column {
        val totalCells = daysInMonth + firstDayOfWeek
        val weeks = (totalCells / 7f).toInt() + if (totalCells % 7 != 0) 1 else 0

        repeat(weeks) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (i in 0..6) {
                    val cellIndex = it * 7 + i
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (cellIndex >= firstDayOfWeek && day <= daysInMonth) {
                            val thisDate = month.atDay(day)
                            DayCell(
                                day = day,
                                isToday = thisDate == today
                            )
                            day++
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
fun DayCell(day: Int, isToday: Boolean) {
    val bgColor = if (isToday) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
    val textColor = if (isToday) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground

    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            color = textColor,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}