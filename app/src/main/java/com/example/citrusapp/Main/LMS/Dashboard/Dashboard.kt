package com.example.citrusapp.Main.LMS.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.R

@Composable
fun DashboardTab(listState: LazyListState) {
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Welcome back, Art!",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Here's what's happening today.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            TaskOverviewCard(
                pendingTasks = 6,
                totalTasks = 10
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            Text(
                text = "Recent Activity",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        itemsIndexed(List(5) {
            "Reminder: Submit Midterm Project" to "Due: Oct 15 • Mobile App Dev"
        }) { index, (title, subtitle) ->
            RecentActivityCard(
                title = title,
                subtitle = subtitle,
                showDivider = index < 4 // Show divider except for last item
            )
        }
    }
}

@Composable
fun TaskOverviewCard(
    pendingTasks: Int = 6,
    totalTasks: Int = 10
) {
    val progress = if (totalTasks > 0) pendingTasks / totalTasks.toFloat() else 0f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(72.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 8.dp,
                )
                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("Task Overview", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("Pending: $pendingTasks / $totalTasks", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text("• 3 Quizzes Pending", fontSize = 13.sp)
                Text("• 2 Assignments", fontSize = 13.sp)
                Text("• 1 Project", fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun RecentActivityCard(title: String, subtitle: String, showDivider: Boolean) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.megaphone),
                contentDescription = "Announcement Icon",
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = title,
                    fontSize = 14.sp
                )
                Text(
                    text = subtitle,
                    fontSize = 11.sp
                )
            }
        }

        if (showDivider) {
            HorizontalDivider()
        }
    }
}
