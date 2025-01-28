package com.example.uas_a16.ui.ViewModel.kategori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.KategoriRepository
import com.example.uas_a16.model.Kategori
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class InsertKategoriUiState {
    object Success : InsertKategoriUiState()  // Ketika insert kategori berhasil
    object Error : InsertKategoriUiState()   // Ketika terjadi error saat insert kategori
    object Loading : InsertKategoriUiState() // Ketika proses insert kategori sedang berlangsung
}
class InsertKategoriViewModel(private val kategoriRepository: KategoriRepository) : ViewModel() {
    // State untuk menangani status operasional
    private val _insertKategoriUiState =
        MutableStateFlow<InsertKategoriUiState>(InsertKategoriUiState.Loading)
    val insertKategoriUiState: StateFlow<InsertKategoriUiState> = _insertKategoriUiState

    // Fungsi untuk menambahkan kategori
    fun insertKategori(kategori: Kategori) {
        viewModelScope.launch {
            _insertKategoriUiState.value = InsertKategoriUiState.Loading
            try {
                if (kategori.namaKategori.isBlank()) {
                    throw IllegalArgumentException("Nama kategori tidak boleh kosong")
                }
                kategoriRepository.insertKategori(kategori)
                _insertKategoriUiState.value = InsertKategoriUiState.Success
            } catch (e: Exception) {
                _insertKategoriUiState.value = InsertKategoriUiState.Error
            }
        }
    }
}