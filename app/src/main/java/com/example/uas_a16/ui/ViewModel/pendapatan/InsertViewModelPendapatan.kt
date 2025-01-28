package com.example.uas_a16.ui.ViewModel.pendapatan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.PendapatanRepository
import com.example.uas_a16.model.Pendapatan
import kotlinx.coroutines.launch

class InsertPendapatanViewModel(private val pendapatanRepository: PendapatanRepository) : ViewModel() {

    var uiState by mutableStateOf(InsertPendapatanUiState())
        private set

    fun updateInsertPendapatanState(insertPendapatanEvent: InsertPendapatanEvent) {
        uiState = uiState.copy(insertPendapatanEvent = insertPendapatanEvent)
    }

    suspend fun insertPendapatan() {
        viewModelScope.launch {
            try {
                val pendapatan = uiState.insertPendapatanEvent.toPendapatan()
                pendapatanRepository.insertPendapatan(pendapatan)
                pendapatanRepository.updatePendapatan(pendapatan.idAset, pendapatan.total)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertPendapatanUiState(
    val insertPendapatanEvent: InsertPendapatanEvent = InsertPendapatanEvent()
)

data class InsertPendapatanEvent(
    val idAset: Int = 0,
    val idKategori: Int = 0,
    val total: Double = 0.0,
    val tanggalTransaksi: String = "", // default to current date
    val catatan: String = ""
)

fun InsertPendapatanEvent.toPendapatan(): Pendapatan = Pendapatan(
    idAset = idAset,
    idKategori = idKategori,
    total = total,
    tanggalTransaksi = tanggalTransaksi,
    catatan = catatan
)

fun Pendapatan.toUiStatePendapatan(): InsertPendapatanUiState = InsertPendapatanUiState(
    insertPendapatanEvent = toInsertPendapatanEvent()
)

fun Pendapatan.toInsertPendapatanEvent(): InsertPendapatanEvent = InsertPendapatanEvent(
    idAset = idAset,
    idKategori = idKategori,
    total = total,
    tanggalTransaksi = tanggalTransaksi,
    catatan = catatan
)
