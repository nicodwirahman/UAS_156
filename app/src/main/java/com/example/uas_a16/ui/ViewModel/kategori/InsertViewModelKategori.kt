package com.example.uas_a16.ui.ViewModel.kategori
sealed class InsertKategoriUiState {
    object Success : InsertKategoriUiState()  // Ketika insert kategori berhasil
    object Error : InsertKategoriUiState()   // Ketika terjadi error saat insert kategori
    object Loading : InsertKategoriUiState() // Ketika proses insert kategori sedang berlangsung
}
