package com.naguirrel.plataslabs8_muchos.ui.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.naguirrel.plataslabs8_muchos.data.Character
import com.naguirrel.plataslabs8_muchos.ui.common.ErrorView
import com.naguirrel.plataslabs8_muchos.ui.common.LoadingView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    onCharacterClick: (Int) -> Unit,
    vm: CharactersViewModel = viewModel()
) {
    val ui by vm.state.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Characters") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { inner ->
        when {
            ui.isLoading -> LoadingView("Cargando")
            ui.hasError -> ErrorView("Error al obtener listado de personajes.\nIntenta de nuevo") { vm.load() }
            else -> {
                val characters = ui.data.orEmpty()
                LazyColumn(modifier = Modifier.padding(inner)) {
                    items(characters, key = { it.id }) { c: Character ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onCharacterClick(c.id) }
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = c.image,
                                contentDescription = c.name,
                                modifier = Modifier.size(48.dp).clip(CircleShape)
                            )
                            Spacer(Modifier.width(16.dp))
                            Column(Modifier.weight(1f)) {
                                Text(
                                    c.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text("${c.species} - ${c.status}", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}
