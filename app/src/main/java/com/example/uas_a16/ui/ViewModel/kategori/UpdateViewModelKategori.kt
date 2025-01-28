package com.example.uas_a16.ui.ViewModel.kategori

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.model.Kategori
import com.example.uas_a16.Repository.KategoriRepository
import kotlinx.coroutines.launch

sealed class UpdateKategoriUiState {
    object Success : UpdateKategoriUiState()
    object Error : UpdateKategoriUiState()
    object Loading : UpdateKategoriUiState()
}

class UpdateKategoriViewModel(private val kategoriRepository: KategoriRepository) : ViewModel() {
    var updateKategoriUiState = mutableStateOf<UpdateKategoriUiState>(UpdateKategoriUiState.Loading)
        private set

    fun updateKategori(kategori: Kategori) {
        viewModelScope.launch {
            updateKategoriUiState.value = UpdateKategoriUiState.Loading
            try {
                if (kategori.namaKategori.isBlank()) {
                    throw IllegalArgumentException("Nama kategori tidak boleh kosong")
                }
                kategoriRepository.updateKategori(kategori) // Gunakan updateKategori, bukan insertKategori
                updateKategoriUiState.value = UpdateKategoriUiState.Success
            } catch (e: Exception) {
                updateKategoriUiState.value = UpdateKategoriUiState.Error
            }
        }
    }
}