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
            try {
                // Ambil list pendapatan dari repository
                val pendapatanList = pendapatanRepository.getAllPendapatan()

                // Periksa apakah pendapatanList kosong atau tidak, kemudian update UI state
                pendapatanUiState = if (pendapatanList.isNotEmpty()) {
                    HomePendapatanUiState.Success(pendapatanList)
                } else {
                    HomePendapatanUiState.Error
                }
            } catch (e: Exception) {
                pendapatanUiState = HomePendapatanUiState.Error
            }
        }
    }

    fun deletePendapatan(asetId: Int) {
        viewModelScope.launch {
            try {
                // Ambil pendapatan berdasarkan ID Aset
                val pendapatan = pendapatanRepository.getPendapatanById(asetId)

                // Jika pendapatan ditemukan, hapus pendapatan tersebut
                pendapatanRepository.deletePendapatan(pendapatan.idAset)

                // Memperbarui UI jika penghapusan berhasil
                getPendapatan()
            } catch (e: Exception) {
                pendapatanUiState = HomePendapatanUiState.Error
            }
        }
    }
}
