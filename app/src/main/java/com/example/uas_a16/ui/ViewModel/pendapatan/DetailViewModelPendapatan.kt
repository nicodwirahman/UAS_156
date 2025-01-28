package com.example.uas_a16.ui.ViewModel.pendapatan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.uas_a16.model.Pendapatan



import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.PendapatanRepository
import com.example.uas_a16.ui.navigasi.DestinasiUpdate
import kotlinx.coroutines.launch
class DetailPendapatanViewModel(
    savedStateHandle: SavedStateHandle,
    private val pendapatanRepository: PendapatanRepository
) : ViewModel() {

    // Pastikan tipe data untuk idAset jelas (Int)
    private val idAset: Int = savedStateHandle[DestinasiUpdate.Kode]
        ?: throw IllegalArgumentException("ID Aset tidak valid atau null")

    // State untuk UI
    var detailUiState: DetailPendapatanUiState by mutableStateOf(DetailPendapatanUiState())
        private set

    // LiveData untuk menyimpan daftar pendapatan berdasarkan idAset
    private val _pendapatanByAset: LiveData<List<Pendapatan>> =
        pendapatanRepository.getPendapatanByAset(idAset)

    // Expose LiveData ke UI
    val pendapatanByAset: LiveData<List<Pendapatan>>
        get() = _pendapatanByAset

    init {
        getPendapatanByAset()
    }

    private fun getPendapatanByAset() {
        viewModelScope.launch {
            detailUiState = DetailPendapatanUiState(isLoading = true)
            try {
                // Ambil daftar pendapatan berdasarkan ID Aset
                val result = pendapatanRepository.getPendapatanByAset(idAset).value
                if (result != null) {
                    detailUiState = DetailPendapatanUiState(
                        pendapatanList = result,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                detailUiState = DetailPendapatanUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Terjadi kesalahan tak terduga"
                )
            }
        }
    }
}

data class DetailPendapatanUiState(
    val detailUiEvent: InsertPendapatanEvent = InsertPendapatanEvent(),
    val pendapatanList: List<Pendapatan> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == InsertPendapatanEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertPendapatanEvent()
}

// Fungsi konversi dari Pendapatan ke InsertPendapatanEvent
fun Pendapatan.toDetailPendapatanUiEvent(): InsertPendapatanEvent {
    return InsertPendapatanEvent(
        idKategori = idKategori, // Tetap menggunakan Int
        total = total,
        tanggalTransaksi = tanggalTransaksi,
        catatan = catatan
    )
}
