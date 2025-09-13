package com.naguirrel.plataslabs8_muchos.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.naguirrel.plataslabs8_muchos.R

@Composable
fun LoginScreen(
    onStart: () -> Unit,
    nombreYCarne: String = "Norman Aguirre – #24479"
) {
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.rnmlogo),
                contentDescription = "Logo"
            )
            Spacer(Modifier.height(32.dp))
            Button(onClick = onStart, modifier = Modifier.fillMaxWidth(0.7f)) {
                Text("Empezar")
            }
        }
        Text(
            text = nombreYCarne,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )
    }
}
