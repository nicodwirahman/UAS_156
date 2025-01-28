package com.example.uas_a16.ui.ViewModel.pendapatan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uas_a16.Repository.PendapatanRepository
import com.example.uas_a16.model.Pendapatan
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomePendapatanUiState {
    data class Success(val pendapatan: List<Pendapatan>) : HomePendapatanUiState()
    object Error : HomePendapatanUiState()
    object Loading : HomePendapatanUiState()
}

class HomePendapatanViewModel(private val pendapatanRepository: PendapatanRepository) : ViewModel() {
    var pendapatanUiState: HomePendapatanUiState by mutableStateOf(HomePendapatanUiState.Loading)
        private set

    init {
        getPendapatan()
    }

    fun getPendapatan() {
        viewModelScope.launch {
            pendapatanUiState = HomePendapatanUiState.Loading
            pendapatanUiState = try {
                // Ambil list pendapatan langsung tanpa akses properti 'data'
                val pendapatanList = pendapatanRepository.getAllPendapatan().value ?: emptyList()
                HomePendapatanUiState.Success(pendapatanList)
            } catch (e: Exception) {
                HomePendapatanUiState.Error
            }
        }
    }

    fun deletePendapatan(aset: Int) {
        viewModelScope.launch {
            try {
                // Ambil data pendapatan berdasarkan aset
                val pendapatanList = pendapatanRepository.getPendapatanByAset(aset).value

                // Periksa apakah ada data pendapatan yang ditemukan
                val pendapatan = pendapatanList?.firstOrNull()

                // Jika ditemukan, hapus pendapatan
                if (pendapatan != null) {
                    pendapatanRepository.deletePendapatan(pendapatan)
                }
            } catch (e: IOException) {
                pendapatanUiState = HomePendapatanUiState.Error
            } catch (e: HttpException) {
                pendapatanUiState = HomePendapatanUiState.Error
            }
        }
    }
}
