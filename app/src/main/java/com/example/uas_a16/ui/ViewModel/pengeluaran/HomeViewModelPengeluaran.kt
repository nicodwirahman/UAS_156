package com.example.uas_a16.ui.ViewModel.pengeluaran

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uas_a16.Repository.PengeluaranRepository
import com.example.uas_a16.model.Pengeluaran
import kotlinx.coroutines.launch
import java.io.IOException
class HomePengeluaranViewModel(private val pengeluaranRepository: PengeluaranRepository) : ViewModel() {
    var pengeluaranUiState: HomePengeluaranUiState by mutableStateOf(HomePengeluaranUiState.Loading)
        private set

    init {
        getPengeluaran()
    }

    // Fungsi untuk mengambil data pengeluaran
    fun getPengeluaran() {
        viewModelScope.launch {
            pengeluaranUiState = HomePengeluaranUiState.Loading
            try {
                val pengeluaranList = pengeluaranRepository.getAllPengeluaran() // Tidak perlu menggunakan .value
                pengeluaranUiState = HomePengeluaranUiState.Success(pengeluaranList)
            } catch (e: Exception) {
                pengeluaranUiState = HomePengeluaranUiState.Error
            }
        }
    }

    // Fungsi untuk menghapus pengeluaran
    fun deletePengeluaran(asetId: Int) {
        viewModelScope.launch {
            try {
                // Ambil data pengeluaran berdasarkan idAset
                val pengeluaran = pengeluaranRepository.getPengeluaranById(asetId)
                // Hapus pengeluaran berdasarkan ID
                pengeluaranRepository.deletePengeluaran(asetId) // Kirim ID, bukan objek
                // Refresh data setelah menghapus
                getPengeluaran()
            } catch (e: Exception) {
                pengeluaranUiState = HomePengeluaranUiState.Error
            }
        }
    }
}

// Definisi UI State untuk pengeluaran
sealed class HomePengeluaranUiState {
    data class Success(val pengeluaran: List<Pengeluaran>) : HomePengeluaranUiState()
    object Error : HomePengeluaranUiState()
    object Loading : HomePengeluaranUiState()
}
