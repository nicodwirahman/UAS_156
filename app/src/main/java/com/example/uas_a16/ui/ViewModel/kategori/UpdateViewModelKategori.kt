package com.example.uas_a16.ui.ViewModel.kategori



sealed class UpdateKategoriUiState {
    object Success : UpdateKategoriUiState()
    object Error : UpdateKategoriUiState()
    object Loading : UpdateKategoriUiState()
}
