package com.example.uas_a16.ui.view.pengeluaran



import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_a16.ui.ViewModel.pengeluaran.HomePengeluaranViewModel
import com.example.uas_a16.model.Pengeluaran
import com.example.uas_a16.ui.ViewModel.pengeluaran.HomePengeluaranUiState
import com.example.uas_a16.ui.view.PenyediaModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePengeluaranScreen(
    modifier: Modifier = Modifier,
    viewModel: HomePengeluaranViewModel = viewModel(factory = PenyediaModel.Factory),
    navigateToInsert: () -> Unit,
    navigateToDetail: (Int) -> Unit // Tambahkan parameter ini
) {
    val pengeluaranUiState = viewModel.pengeluaranUiState

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Daftar Pengeluaran") },
                actions = {
                    IconButton(onClick = navigateToInsert) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Pengeluaran")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Card untuk input data pengeluaran
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Input Pengeluaran", style = MaterialTheme.typography.titleMedium)
                    // Tambahkan form input di sini (bisa menggunakan komponen yang sudah ada)
                }
            }

            // Daftar pengeluaran
            when (pengeluaranUiState) {
                is HomePengeluaranUiState.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is HomePengeluaranUiState.Error -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text("Terjadi kesalahan, coba lagi.")
                    }
                }
                is HomePengeluaranUiState.Success -> {
                    PengeluaranList(
                        pengeluaranList = pengeluaranUiState.pengeluaran,
                        onDeletePengeluaran = { asetId ->
                            viewModel.deletePengeluaran(asetId)
                        },
                        onDetailPengeluaran = navigateToDetail, // Teruskan parameter ini
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}


@Composable
fun PengeluaranList(
    pengeluaranList: List<Pengeluaran>,
    onDeletePengeluaran: (Int) -> Unit, // Parameter ini menerima idAset (Int)
    onDetailPengeluaran: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        pengeluaranList.forEach { pengeluaran ->
            PengeluaranItem(
                pengeluaran = pengeluaran,
                onDelete = { onDeletePengeluaran(pengeluaran.idAset) }, // Kirim idAset (Int)
                onDetail = { onDetailPengeluaran(pengeluaran.idAset) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }
    }
}
@Composable
fun PengeluaranItem(
    pengeluaran: Pengeluaran,
    onDelete: () -> Unit,
    onDetail: () -> Unit, // Tambahkan parameter ini
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Red) // Ubah warna card menjadi merah
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "ID Aset: ${pengeluaran.idAset}")
                Text(text = "Total Pengeluaran: ${pengeluaran.total}")
                Text(text = "Catatan: ${pengeluaran.catatan}")
            }
            Row {
                IconButton(onClick = onDetail) { // Icon untuk melihat detail
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Detail Pengeluaran")
                }
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Hapus Pengeluaran")
                }
            }
        }
    }
}