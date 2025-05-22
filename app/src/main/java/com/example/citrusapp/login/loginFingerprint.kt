package com.example.citrusapp.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citrusapp.R
import com.example.citrusapp.ui.theme.blue_green

@Composable
fun FingerprintScreen(mainLoginClick: () -> Unit, signupClick: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var hasSubmittedEmail by remember { mutableStateOf(false) }
    val isEmailValid = remember(email) {
        email.matches(Regex("^[A-Za-z0-9._%+-]+@(gmail|yahoo)\\.com$"))
    }

    val passwordFocusRequester = remember { FocusRequester() }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp)
    ) {
        // Header
        IconButton(
            onClick = { mainLoginClick() },
            modifier = Modifier
                .height(46.dp)
                .padding(top = 8.dp, start = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back"
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Image(
            painter = painterResource(id = R.drawable.fingerprint),
            contentDescription = "Fingerprint Icon",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(80.dp)
                .align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Title
        Text(
            text = "Login using Fingerprint",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.5.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Enter your existing email and password that you wanted to be automatically logged in using your fingerprint. NOTE that you can still change the account that is saved later in your 'logged in' account settings.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.sp,
                lineHeight = 16.sp
            ),
            modifier = Modifier
                .padding(start = 12.dp, top = 12.dp, end = 12.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                if (hasSubmittedEmail) hasSubmittedEmail = false
            },
            label = { Text("Email") },
            isError = hasSubmittedEmail && !isEmailValid,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    hasSubmittedEmail = true
                    if (isEmailValid) {
                        passwordFocusRequester.requestFocus()
                    }
                }
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = "Email Icon"
                )
            },
            supportingText = {
                Text(
                    text = if (hasSubmittedEmail && !isEmailValid) "Please enter a valid email." else " ",
                    color = if (hasSubmittedEmail && !isEmailValid) MaterialTheme.colorScheme.error else Color.Transparent
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_password),
                    contentDescription = "Password Icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(
                            id = if (passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility
                        ),
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester)
                .padding(start = 16.dp, end = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    // TODO: Navigate to Login
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue_green,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 12.dp, end = 12.dp)
            ) {
                Text(text = "Scan Fingerprint")
            }

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "Don't have an account?",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Signup",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = blue_green,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable(
                        onClick = {
                            signupClick() // TODO: Navigate to Signup
                        },
                        role = Role.Button
                    )
                    .indication(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true)
                    )
            )
            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Developed by DCODE",
                fontSize = 14.sp,
                modifier = Modifier
                    .alpha(0.5f)
            )
        }
    }
}

