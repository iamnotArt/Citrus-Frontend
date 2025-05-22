package com.example.citrusapp.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.R

@Composable
fun FingerprintScreen(mainLoginClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 18.dp, start = 16.dp) // Add padding here instead of aligning the Column
    ) {
        IconButton(
            onClick = { mainLoginClick() },
            modifier = Modifier
                .height(46.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back"
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Login with",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(start = 18.dp, top = 16.dp)
        )
    }
}
