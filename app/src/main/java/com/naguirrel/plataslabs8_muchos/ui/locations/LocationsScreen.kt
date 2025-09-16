package com.naguirrel.plataslabs8_muchos.ui.locations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.naguirrel.plataslabs8_muchos.data.Location
import com.naguirrel.plataslabs8_muchos.data.LocationDb

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsScreen(
    onLocationClick: (Int) -> Unit
) {
    val db = remember { LocationDb() }
    val locations = remember { db.getAllLocations() }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Locations") }) }
    ) { inner ->
        LazyColumn(modifier = Modifier.padding(inner)) {
            items(locations, key = { it.id }) { loc: Location ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onLocationClick(loc.id) }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(loc.name, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(2.dp))
                    Text(loc.type, style = MaterialTheme.typography.bodyMedium)
                }
                HorizontalDivider()
            }
        }
    }
}
