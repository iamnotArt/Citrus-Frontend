import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.R
import com.example.citrusapp.ui.theme.blue_green

@Composable
fun SlideOne() {
    // State for text fields
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Top Title and Description
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 48.dp)
        ) {
            Text(
                text = "Profile Details",
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp
            )
            Text(
                text = "Tell us your name so people can know who you are!",
                fontSize = 14.sp,
                lineHeight = 16.sp,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        // Center content with two text fields
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(horizontal = 16.dp)
                    .alpha(0.7f)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = { Text("First Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_pencil),
                                contentDescription = "Email Icon"
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Last Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_pencil),
                                contentDescription = "Email Icon"
                            )
                        }
                    )
                }
            }
        }

        // Bottom Button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 112.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "We'll only use your name to create a unique identity within the app, so please make sure it's accurate.",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp,
                modifier = Modifier
                .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
            )
            Button(
                onClick = {
                    // TODO: Use firstName and lastName or navigate
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue_green,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 12.dp)
            ) {
                Text(text = "Next")
            }
        }
    }
}
