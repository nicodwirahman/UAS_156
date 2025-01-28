package com.example.uas_a16.ui.ViewModel.pengeluaran

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.PengeluaranRepository
import com.example.uas_a16.ui.navigasi.DestinasiUpdate
import kotlinx.coroutines.launch

class UpdatePengeluaranViewModel(
    savedStateHandle: SavedStateHandle,
    private val pengeluaranRepository: PengeluaranRepository
) : ViewModel() {
    var updateUiState by mutableStateOf(InsertPengeluaranUiState())
        private set

    private val _Aset: Int = checkNotNull(savedStateHandle[DestinasiUpdate.route]) // Memastikan ID pengeluaran ada di SavedState

    init {
        // Mengambil data Pengeluaran berdasarkan ID Aset secara async dalam coroutine
        viewModelScope.launch {
            try {
                val pengeluaran = pengeluaranRepository.getPengeluaranById(_Aset)
                pengeluaran?.let {
                    updateUiState = it.toUiStatePengeluaran() // Sesuaikan dengan cara Anda mengonversi pengeluaran ke UI State
                }
            } catch (e: Exception) {
                // Tangani jika terjadi kesalahan
                e.printStackTrace()
            }
        }
    }

    // Fungsi untuk memperbarui UI State dengan event yang baru
    fun updateInsertPengeluaranState(insertPengeluaranEvent: InsertPengeluaranEvent) {
        updateUiState = InsertPengeluaranUiState(insertPengeluaranEvent = insertPengeluaranEvent)
    }

    // Fungsi untuk memperbarui pengeluaran
    suspend fun updatePengeluaran() {
        viewModelScope.launch {
            try {
                pengeluaranRepository.updatePengeluaran(_Aset, updateUiState.insertPengeluaranEvent.toPengeluaran())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
