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

