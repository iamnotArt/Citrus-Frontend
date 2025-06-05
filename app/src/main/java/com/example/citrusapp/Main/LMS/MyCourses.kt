package com.example.citrusapp.Main.LMS

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyCoursesTab(listState: LazyListState) {
    val courses = listOf(
        Course("Mobile App Development", "BSCS 3A", "Mr. Dela Cruz", Color(0xFF4CAF50)),
        Course("Web Development", "BSCS 3B", "Ms. Santos", Color(0xFF2196F3)),
        Course("Data Structures", "BSCS 2A", "Dr. Reyes", Color(0xFFFF9800)),
        Course("Machine Learning", "BSCS 4A", "Prof. Navarro", Color(0xFF9C27B0)),
        Course("Software Engineering", "BSCS 3A", "Engr. Gomez", Color(0xFFE91E63)),
        Course("Mobile App Development", "BSCS 3A", "Mr. Dela Cruz", Color(0xFF4CAF50)),
        Course("Web Development", "BSCS 3B", "Ms. Santos", Color(0xFF2196F3)),
        Course("Data Structures", "BSCS 2A", "Dr. Reyes", Color(0xFFFF9800)),
        Course("Machine Learning", "BSCS 4A", "Prof. Navarro", Color(0xFF9C27B0))
    )

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(courses.size) { index ->
            val course = courses[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    // Top colored strip
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                            .background(course.color)
                    )

                    // Course content
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {


                        Column {
                            Text(
                                text = course.title,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = course.section,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                            Text(
                                text = "Instructor: ${course.instructor}",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

data class Course(
    val title: String,
    val section: String,
    val instructor: String,
    val color: Color
)
