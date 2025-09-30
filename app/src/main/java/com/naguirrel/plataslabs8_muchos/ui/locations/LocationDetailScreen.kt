package com.naguirrel.plataslabs8_muchos.ui.locations

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.naguirrel.plataslabs8_muchos.ui.common.ErrorView
import com.naguirrel.plataslabs8_muchos.ui.common.LoadingView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailScreen(
    id: Int,
    onBack: () -> Unit,
    vm: LocationDetailViewModel = viewModel()
) {
    val ui by vm.state.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Location details") },
                navigationIcon = { IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                } }
            )
        }
    ) { inner ->
        when {
            ui.isLoading -> LoadingView("Cargando")
            ui.hasError -> ErrorView("Error al obtener la ubicación.\nIntenta de nuevo") { vm.load() }
            else -> {
                val loc = ui.data!!
                Column(
                    modifier = Modifier
                        .padding(inner)
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .fillMaxSize()
                ) {
                    InfoRow("ID:", loc.id.toString())
                    InfoRow("Name:", loc.name)
                    InfoRow("Type:", loc.type)
                    InfoRow("Dimension:", loc.dimension)
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium)
        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}
