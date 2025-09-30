package com.naguirrel.plataslabs8_muchos.ui.locations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.naguirrel.plataslabs8_muchos.data.Location
import com.naguirrel.plataslabs8_muchos.ui.common.ErrorView
import com.naguirrel.plataslabs8_muchos.ui.common.LoadingView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsScreen(
    onLocationClick: (Int) -> Unit,
    vm: LocationsViewModel = viewModel()
) {
    val ui by vm.state.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Locations") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { inner ->
        when {
            ui.isLoading -> LoadingView("Cargando")
            ui.hasError -> ErrorView("Error al obtener listado de ubicaciones.\nIntenta de nuevo") { vm.load() }
            else -> {
                val locations = ui.data.orEmpty()
                LazyColumn(modifier = Modifier.padding(inner)) {
                    items(locations, key = { it.id }) { loc: Location ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onLocationClick(loc.id) }
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                        ) {
                            Text(loc.name, style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary)
                            Spacer(Modifier.height(2.dp))
                            Text(loc.type, style = MaterialTheme.typography.bodyMedium)
                        }
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}
