package com.example.uas_a16.ui.ViewModel.pengeluaran
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.model.Pengeluaran
import com.example.uas_a16.Repository.PengeluaranRepository
import kotlinx.coroutines.launch
import java.util.Date

class InsertPengeluaranViewModel(private val pengeluaranRepository: PengeluaranRepository) : ViewModel() {

    var uiState by mutableStateOf(InsertPengeluaranUiState())
        private set

    fun updateInsertPengeluaranState(insertPengeluaranEvent: InsertPengeluaranEvent) {
        uiState = uiState.copy(insertPengeluaranEvent = insertPengeluaranEvent)
    }

    suspend fun insertPengeluaran() {
        viewModelScope.launch {
            try {
                val pengeluaran = uiState.insertPengeluaranEvent.toPengeluaran()
                pengeluaranRepository.insertPengeluaran(pengeluaran)
                uiState = uiState.copy(isSuccess = true, successMessage = "Pengeluaran berhasil disimpan")
            } catch (e: Exception) {
                uiState = uiState.copy(isError = true, errorMessage = e.message ?: "Gagal menyimpan pengeluaran")
            }
        }
    }
}

data class InsertPengeluaranUiState(
    val insertPengeluaranEvent: InsertPengeluaranEvent = InsertPengeluaranEvent(),
    val isSuccess: Boolean = false,
    val successMessage: String = "",
    val isError: Boolean = false,
    val errorMessage: String = "",
    val showDialog: Boolean = false // Untuk menampilkan dialog konfirmasi
)

data class InsertPengeluaranEvent(
    val idAset: Int = 0,
    val idKategori: Int = 0,
    val total: Double = 0.0,
    val tanggalTransaksi: String = "", // default to current date
    val catatan: String = ""
)

fun InsertPengeluaranEvent.toPengeluaran(): Pengeluaran = Pengeluaran(
    idAset = idAset,
    idKategori = idKategori,
    total = total,
    tanggalTransaksi = tanggalTransaksi,
    catatan = catatan
)

fun Pengeluaran.toUiStatePengeluaran(): InsertPengeluaranUiState = InsertPengeluaranUiState(
    insertPengeluaranEvent = toInsertPengeluaranEvent()
)

fun Pengeluaran.toInsertPengeluaranEvent(): InsertPengeluaranEvent = InsertPengeluaranEvent(
    idAset = idAset,
    idKategori = idKategori,
    total = total,
    tanggalTransaksi = tanggalTransaksi,
    catatan = catatan
)
