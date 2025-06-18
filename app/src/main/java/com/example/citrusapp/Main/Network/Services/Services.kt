package com.example.citrusapp.Main.Network.Services

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.R

@Composable
fun ServicesTab() {
    val listState = rememberLazyListState()
    var searchQuery by remember { mutableStateOf("") }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search for Services...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search Icon"
                    )
                }
            )
        }

        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Service Marketplace",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 34.dp),
                    fontSize = 22.sp
                )
                Text(
                    text = "Post and discover services like projects for sale, tutoring, editing, and more. Connect with others and showcase your skills!",
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
                    fontSize = 14.sp,
                    lineHeight = 16.sp
                )
            }
        }

        items(20) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp, RoundedCornerShape(16.dp)),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Job title didi
                    Text(
                        text = "Software Engineer",
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Company + Location didi
                    Text(
                        text = "Tech Solutions Inc. • Cebu City",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    // Salary didi (optional)
                    Text(
                        text = "₱25,000 - ₱40,000 per month",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    // Type or Posting Date didi
                    Text(
                        text = "Full-time • Posted 3 days ago",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        }
    }
}