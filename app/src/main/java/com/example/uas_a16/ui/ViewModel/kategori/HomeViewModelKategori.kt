package com.example.uas_a16.ui.ViewModel.kategori


import com.example.uas_a16.model.Kategori


sealed class HomeKategoriUiState {
    data class Success(val kategori: List<Kategori>) : HomeKategoriUiState()
    object Error : HomeKategoriUiState()
    object Loading : HomeKategoriUiState()
}

