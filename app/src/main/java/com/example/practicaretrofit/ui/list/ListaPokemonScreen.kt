package com.example.practicaretrofit.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Button

import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp



@Composable
fun ListaPokemonScreen(viewModel: ListaPokemonViewModel = viewModel()) {
    val estado by viewModel.uiState.collectAsState()
    val error = estado.error

    when {
        estado.cargando -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        error != null -> MensajeDeError(mensaje = error, onReintentar = viewModel::cargar)
        else -> LazyColumn {
            items(estado.pokemones, key = { it.id }) { pokemon ->
                Text("#${pokemon.id} ${pokemon.nombre}", modifier = Modifier.padding(16.dp))

            }
        }
    }
}

@Composable
fun MensajeDeError(mensaje: String, onReintentar: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement. Center

    ) {

        Text(text = mensaje, color = MaterialTheme.colorScheme.error, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onReintentar) { Text("Reintentar") }

    }

}
