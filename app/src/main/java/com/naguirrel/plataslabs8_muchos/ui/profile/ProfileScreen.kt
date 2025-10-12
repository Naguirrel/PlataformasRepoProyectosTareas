package com.naguirrel.plataslabs8_muchos.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onLoggedOut: () -> Unit, vm: ProfileViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val name by vm.nameFlow.collectAsState(initial = "")

    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text("Profile") }) }) { inner ->
        Column(
            modifier = Modifier.padding(inner).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // espacio para imagen de perfil
            Spacer(Modifier.height(80.dp))
            Text("Nombre: ${name ?: ""}")
            Text("Carné: 24479")
            Spacer(Modifier.height(24.dp))
            Button(onClick = {
                scope.launch {
                    vm.logout()
                    onLoggedOut()
                }
            }) { Text("Cerrar sesión") }
        }
    }
}
