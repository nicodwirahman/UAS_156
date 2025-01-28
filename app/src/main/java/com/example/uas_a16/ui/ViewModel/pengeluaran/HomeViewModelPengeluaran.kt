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

sealed class HomePengeluaranUiState {
    data class Success(val pengeluaran: List<Pengeluaran>) : HomePengeluaranUiState()
    object Error : HomePengeluaranUiState()
    object Loading : HomePengeluaranUiState()
}

class HomePengeluaranViewModel(private val pengeluaranRepository: PengeluaranRepository) : ViewModel() {
    var pengeluaranUiState: HomePengeluaranUiState by mutableStateOf(HomePengeluaranUiState.Loading)
        private set

    init {
        getPengeluaran()
    }

    fun getPengeluaran() {
        viewModelScope.launch {
            pengeluaranUiState = HomePengeluaranUiState.Loading
            pengeluaranUiState = try {
                // Ambil list pengeluaran langsung tanpa akses properti 'data'
                val pengeluaranList = pengeluaranRepository.getAllPengeluaran().value ?: emptyList()
                HomePengeluaranUiState.Success(pengeluaranList)
            } catch (e: Exception) {
                HomePengeluaranUiState.Error
            }
        }
    }


    fun deletePengeluaran(aset: Int) {
        viewModelScope.launch {
            try {
                // Ambil data pengeluaran berdasarkan aset
                val pengeluaranList = pengeluaranRepository.getPengeluaranByAset(aset).value

                // Periksa apakah ada data pengeluaran yang ditemukan
                val pengeluaran = pengeluaranList?.firstOrNull()

                // Jika ditemukan, hapus pengeluaran
                if (pengeluaran != null) {
                    pengeluaranRepository.deletePengeluaran(pengeluaran)
                }
            } catch (e: IOException) {
                pengeluaranUiState = HomePengeluaranUiState.Error
            } catch (e: HttpException) {
                pengeluaranUiState = HomePengeluaranUiState.Error
            }
        }
    }
}