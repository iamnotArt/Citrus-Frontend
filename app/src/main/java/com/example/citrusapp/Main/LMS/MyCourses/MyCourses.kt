package com.example.citrusapp.Main.LMS.MyCourses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.R
import com.example.citrusapp.ui.theme.blue_green
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCoursesTab(listState: LazyListState) {
    val courses = listOf(
        Course("Mobile App Development", "BSCS 3A", "Mr. Dela Cruz"),
        Course("Web Development", "BSCS 3B", "Ms. Santos"),
        Course("Data Structures", "BSCS 2A", "Dr. Reyes"),
        Course("Machine Learning", "BSCS 4A", "Prof. Navarro"),
        Course("Software Engineering", "BSCS 3A", "Engr. Gomez"),
        Course("Mobile App Development", "BSCS 3A", "Mr. Dela Cruz"),
        Course("Web Development", "BSCS 3B", "Ms. Santos"),
        Course("Data Structures", "BSCS 2A", "Dr. Reyes"),
        Course("Machine Learning", "BSCS 4A", "Prof. Navarro")
    )

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val showSheet = remember { mutableStateOf(false) }

    if (showSheet.value) {
        AddCourseBottomSheet(
            sheetState = sheetState,
            onDismiss = { showSheet.value = false }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(courses.size) { index ->
                val course = courses[index]
                val baseColor = generateRandomColor(index)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        baseColor.darken(0.4f),
                                        baseColor
                                    )
                                )
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            baseColor.darken(0.4f),
                                            baseColor
                                        )
                                    )
                                )
                        ) {
                            // Title at TopStart
                            Column(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = course.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White,
                                    fontSize = 20.sp
                                )
                            }

                            // Section and Instructor at BottomStart
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = course.section,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                                Text(
                                    text = "Instructor: ${course.instructor}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 12.sp
                                )
                            }

                        }


                        androidx.compose.foundation.Image(
                            painter = painterResource(id = R.drawable.schoollogo),
                            contentDescription = "School Logo",
                            modifier = Modifier
                                .size(200.dp) // Adjust size as needed
                                .align(Alignment.BottomEnd)
                                .offset(x = 80.dp, y = 35.dp) // Push half out of bounds
                                .graphicsLayer {
                                    alpha = 0.5f // Lower opacity here
                                }
                        )

                    }

                }
            }
        }

        FloatingActionButton(
            onClick = { showSheet.value = true }, // ✅ This shows the bottom sheet
            containerColor = blue_green,
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(56.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Add",
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }

    }
}

data class Course(
    val title: String,
    val section: String,
    val instructor: String
)

fun generateRandomColor(index: Int): Color {
    val random = Random(index)
    val r = random.nextInt(100, 150)
    val g = random.nextInt(100, 256)
    val b = random.nextInt(100, 256)
    return Color(r, g, b)
}

fun Color.darken(factor: Float): Color {
    val r = (red * (1f - factor)).coerceIn(0f, 1f)
    val g = (green * (1f - factor)).coerceIn(0f, 1f)
    val b = (blue * (1f - factor)).coerceIn(0f, 1f)
    return Color(r, g, b, alpha)
}
