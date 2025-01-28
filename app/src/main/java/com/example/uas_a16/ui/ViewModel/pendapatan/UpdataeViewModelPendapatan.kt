package com.example.uas_a16.ui.ViewModel.pendapatan

// Sealed class untuk state UI
sealed class UpdatePendapatanUiState {
    object Success : UpdatePendapatanUiState()
    object Error : UpdatePendapatanUiState()
    object Loading : UpdatePendapatanUiState()
}
