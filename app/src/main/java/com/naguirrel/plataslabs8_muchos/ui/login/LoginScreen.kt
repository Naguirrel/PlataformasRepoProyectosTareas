package com.naguirrel.plataslabs8_muchos.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.naguirrel.plataslabs8_muchos.R

@Composable
fun LoginScreen(
    onLogged: () -> Unit,

    vm: LoginViewModel = viewModel()
) {
    val ui by vm.ui.collectAsState()
    var name by remember { mutableStateOf(TextFieldValue("")) }

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(R.drawable.rnmlogo), contentDescription = null)
            Spacer(Modifier.height(24.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                enabled = !ui.isLoading,
                singleLine = true,
                placeholder = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { vm.login(name.text.trim(), onLogged) },
                enabled = name.text.isNotBlank() && !ui.isLoading,
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                if (ui.isLoading) {
                    CircularProgressIndicator(strokeWidth = 2.dp, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                }
                Text("Iniciar sesión")
            }
            ui.error?.let {
                Spacer(Modifier.height(8.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
        Text(
            "Norman Aguirre Lepe - 24479",
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 12.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
