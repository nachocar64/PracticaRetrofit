package com.example.practicaretrofit.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaretrofit.data.PokemonRepository
import com.example.practicaretrofit.model.PokemonResumen
import com.example.practicaretrofit.util.Resultado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ListaUiState(
    val cargando: Boolean = false,
    val pokemones: List<PokemonResumen> = emptyList(),
    val error: String? = null
)
class ListaPokemonViewModel(
    private val repositorio: PokemonRepository = PokemonRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(ListaUiState())
    val uiState: StateFlow<ListaUiState> = _uiState.asStateFlow()

    init {
        cargar()
    }
        fun cargar() {
            viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, error = null) }
                when (val resultado =
                    repositorio.obtenerPokemones(limite = 20, desplazamiento = 0)) {
                    is Resultado.Exito -> _uiState.update {
                        it.copy(
                            cargando = false,
                            pokemones = resultado.datos
                        )
                    }

                    is Resultado.Error -> _uiState.update {
                        it.copy(
                            cargando = false, error =
                                resultado.mensaje
                        )
                    }
                }
            }
        }
    }
