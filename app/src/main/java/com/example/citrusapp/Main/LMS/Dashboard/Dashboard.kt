package com.example.citrusapp.Main.LMS.Dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardTab(listState: LazyListState) {
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Greeting
            Column {
                Text(
                    text = "Welcome back, Art Lyndone!",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Here's what's happening today.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }

        item {
            // AI-Powered Suggestions (Placeholder logic)
            AIPoweredSuggestions()
        }

        item {
            Text(
                text = "Recent Activity",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }

        items(5) { index ->
            RecentActivityCard(
                title = "Reminder: Submit Midterm Project",
                subtitle = "Due: Oct 15 • Mobile App Dev"
            )
        }
    }
}

@Composable
fun AIPoweredSuggestions() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "AI Suggestion",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "AI Suggestion",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Based on your activity, you should start working on your capstone documentation.",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun RecentActivityCard(title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Announcement Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}
