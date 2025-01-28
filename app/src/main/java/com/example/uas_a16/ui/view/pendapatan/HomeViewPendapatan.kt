package com.example.uas_a16.ui.view.pendapatan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_a16.model.Pendapatan
import com.example.uas_a16.ui.ViewModel.pendapatan.HomePendapatanUiState
import com.example.uas_a16.ui.ViewModel.pendapatan.HomePendapatanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePendapatanScreen(
    modifier: Modifier = Modifier,
    viewModel: HomePendapatanViewModel = viewModel(),
    navigateToInsert: () -> Unit,
    onDetailPendapatan: (Int) -> Unit // Tambahkan parameter ini
) {
    // Mengambil state UI pendapatan
    val pendapatanUiState = viewModel.pendapatanUiState

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Daftar Pendapatan") },
                actions = {
                    IconButton(onClick = navigateToInsert) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Pendapatan")
                    }
                }
            )
        }
    ) { innerPadding ->
        when (pendapatanUiState) {
            is HomePendapatanUiState.Loading -> {
                // Menampilkan Loading ketika data sedang diambil
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    CircularProgressIndicator()
                }
            }
            is HomePendapatanUiState.Error -> {
                // Menampilkan pesan error jika terjadi masalah
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text("Terjadi kesalahan, coba lagi.")
                }
            }
            is HomePendapatanUiState.Success -> {
                // Menampilkan daftar pendapatan jika data berhasil diambil
                PendapatanList(
                    pendapatanList = pendapatanUiState.pendapatan,
                    onDeletePendapatan = { asetId ->
                        viewModel.deletePendapatan(asetId)
                    },
                    onDetailPendapatan = onDetailPendapatan, // Teruskan parameter ini
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun PendapatanList(
    pendapatanList: List<Pendapatan>,
    onDeletePendapatan: (Int) -> Unit,
    onDetailPendapatan: (Int) -> Unit, // Tambahkan parameter untuk navigasi ke detail
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        pendapatanList.forEach { pendapatan ->
            PendapatanItem(
                pendapatan = pendapatan,
                onDelete = { onDeletePendapatan(pendapatan.idAset) },
                onDetail = { onDetailPendapatan(pendapatan.idAset) }, // Panggil dengan idAset
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }
    }
}@Composable
fun PendapatanItem(
    pendapatan: Pendapatan,
    onDelete: () -> Unit,
    onDetail: () -> Unit, // Tambahkan parameter untuk navigasi ke detail
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Green) // Ubah warna menjadi hijau
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "ID Aset: ${pendapatan.idAset}")
                Text(text = "Total Pendapatan: ${pendapatan.total}")
                Text(text = "Catatan: ${pendapatan.catatan}")
            }
            Row {
                IconButton(onClick = onDetail) { // Icon untuk melihat detail
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Detail Pendapatan")
                }
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Hapus Pendapatan")
                }
            }
        }
    }
}