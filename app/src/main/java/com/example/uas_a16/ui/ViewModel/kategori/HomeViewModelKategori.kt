package com.example.uas_a16.ui.ViewModel.kategori

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.KategoriRepository
import com.example.uas_a16.model.Kategori
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



sealed class HomeKategoriUiState {
    data class Success(val kategori: List<Kategori>) : HomeKategoriUiState()
    object Error : HomeKategoriUiState()
    object Loading : HomeKategoriUiState()
}

class HomeKategoriViewModel(private val kategoriRepository: KategoriRepository) : ViewModel() {
    private val _kategoriUiState = MutableStateFlow<HomeKategoriUiState>(HomeKategoriUiState.Loading)
    val kategoriUiState: StateFlow<HomeKategoriUiState> = _kategoriUiState

    init {
        getKategori()
    }

    fun updateKategori(kategori: Kategori) {
        viewModelScope.launch {
            try {
                kategoriRepository.updateKategori(kategori.idKategori, kategori) // Gunakan id dan kategori
                getKategori() // Refresh data setelah update
            } catch (e: Exception) {
                _kategoriUiState.value = HomeKategoriUiState.Error
            }
        }
    }

    fun getKategori() {
        viewModelScope.launch {
            _kategoriUiState.value = HomeKategoriUiState.Loading
            try {
                // Ambil list kategori langsung tanpa akses properti 'data'
                val kategoriList = kategoriRepository.getAllKategori() // Panggil getAllKategori
                _kategoriUiState.value = HomeKategoriUiState.Success(kategoriList)
            } catch (e: Exception) {
                _kategoriUiState.value = HomeKategoriUiState.Error
            }
        }
    }

    fun deleteKategori(kategori: Kategori) {
        viewModelScope.launch {
            try {
                kategoriRepository.deleteKategori(kategori.idKategori) // Panggil deleteKategori dengan id
                getKategori() // Refresh data setelah delete
            } catch (e: Exception) {
                _kategoriUiState.value = HomeKategoriUiState.Error
            }
        }
    }
}
