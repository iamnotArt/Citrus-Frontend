package com.example.citrusapp.Main.Network.Services

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.R

@Composable
fun ServicesTab() {
    val listState = rememberLazyListState()

    var text by remember { mutableStateOf("") }

    val onSurfaceColor = MaterialTheme.colorScheme.onSurface

    val customSelectionColors = TextSelectionColors(
        handleColor = onSurfaceColor,
        backgroundColor = onSurfaceColor.copy(alpha = 0.3f))

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            CompositionLocalProvider(LocalTextSelectionColors provides customSelectionColors) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search Icon",
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(modifier = Modifier.fillMaxWidth()) {
                            if (text.isEmpty()) {
                                Text(
                                    text = "Search for Services...",
                                    fontSize = 14.sp,
                                    color = onSurfaceColor.copy(alpha = 0.6f)
                                )
                            }

                            BasicTextField(
                                value = text,
                                onValueChange = { text = it },
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 14.sp,
                                    color = onSurfaceColor
                                ),
                                cursorBrush = SolidColor(onSurfaceColor),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }

        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Service Marketplace",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 18.dp),
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
                    .fillMaxWidth(),
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