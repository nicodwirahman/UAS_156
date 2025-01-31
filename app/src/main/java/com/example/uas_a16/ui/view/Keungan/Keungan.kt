package com.example.uas_a16.ui.view.Keungan

import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.PendapatanRepository
import com.example.uas_a16.Repository.PengeluaranRepository
import com.example.uas_a16.ui.ViewModel.Keuangan.FinanceViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: FinanceViewModel) {
    val saldo by viewModel.saldo.collectAsState()
    val totalPemasukan by viewModel.totalPemasukan.collectAsState()
    val totalPengeluaran by viewModel.totalPengeluaran.collectAsState()

    val saldoColor = if (saldo >= 0) Color.Green else Color.Red

    Column(
        modifier = androidx.compose.ui.Modifier.padding(16.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            modifier = androidx.compose.ui.Modifier.fillMaxWidth()
        ) {
            Column(modifier = androidx.compose.ui.Modifier.padding(16.dp)) {
                Text(
                    text = "Saldo: Rp $saldo",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = saldoColor
                )
                Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))
                Text(text = "Total Pengeluaran: Rp $totalPengeluaran", fontSize = 18.sp)
                Text(text = "Total Pemasukan: Rp $totalPemasukan", fontSize = 18.sp)
            }
        }
    }
}

