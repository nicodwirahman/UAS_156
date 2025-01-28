package com.example.uas_a16.ui.ViewModel.pendapatan
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.PendapatanRepository
import com.example.uas_a16.model.Pendapatan
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

    init {
        getPendapatanByAset()
    }

    private fun getPendapatanByAset() {
        viewModelScope.launch {
            detailUiState = detailUiState.copy(isLoading = true)
            try {
                // Ambil daftar pendapatan berdasarkan ID Aset
                val pendapatanList = pendapatanRepository.getAllPendapatan()

                // Filter pendapatan berdasarkan idAset
                val filteredPendapatanList = pendapatanList.filter { it.idAset == idAset }

                // Update state dengan hasil
                detailUiState = detailUiState.copy(
                    pendapatanList = filteredPendapatanList,
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiState = detailUiState.copy(
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
        idKategori = idKategori,
        total = total,
        tanggalTransaksi = tanggalTransaksi,
        catatan = catatan
    )
}
