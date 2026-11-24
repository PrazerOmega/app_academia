package com.fittrack.app.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Fase 1: expõe apenas o dashboard vazio. Os dados reais chegam na Fase 5. */
class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Success(HomeDashboard.Empty))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    companion object {
        val Factory = viewModelFactory {
            initializer { HomeViewModel() }
        }
    }
}
