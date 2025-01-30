package com.example.uas_a16.ui.view.pendapatan


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_a16.model.Pendapatan
import com.example.uas_a16.ui.ViewModel.pendapatan.DetailPendapatanViewModel
import com.example.uas_a16.ui.ViewModel.pendapatan.DetailPendapatanUiState
import com.example.uas_a16.ui.ViewModel.pendapatan.toPendapatan

import com.example.uas_a16.ui.navigasi.CostumeTopAppBar
import com.example.uas_a16.ui.navigasi.DestinasiDetailPendapatan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPendapatanScreen(
    navigateBack: () -> Unit,
    navigateToEdit: () -> Unit,
    onDelete: () -> Unit, // Tambahkan parameter untuk fungsi hapus
    modifier: Modifier = Modifier, // Parameter modifier digunakan
    viewModel: DetailPendapatanViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailPendapatan.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            Row(
                modifier = modifier
                    .padding(18.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FloatingActionButton(
                    onClick = navigateToEdit,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Pendapatan")
                }
                FloatingActionButton(
                    onClick = onDelete, // Button hapus
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.errorContainer
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Hapus Pendapatan")
                }
            }
        }
    ) { innerPadding ->
        BodyDetailPendapatan(
            detailUiState = viewModel.detailUiState,
            modifier = modifier.padding(innerPadding) // Modifier digunakan di sini
        )
    }
}

@Composable
fun BodyDetailPendapatan(
    detailUiState: DetailPendapatanUiState,
    modifier: Modifier = Modifier // Parameter modifier digunakan
) {
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailUiState.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailUiState.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailUiState.isUiEventNotEmpty -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp) // Modifier digunakan di sini
            ) {
                item {
                    ItemDetailPendapatan(
                        pendapatan = detailUiState.detailUiEvent.toPendapatan(),
                        modifier = modifier // Modifier digunakan di sini
                    )
                }
            }
        }
    }
}

@Composable
fun ItemDetailPendapatan(
    modifier: Modifier = Modifier, // Parameter modifier digunakan
    pendapatan: Pendapatan
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp), // Modifier digunakan di sini
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = modifier
                .padding(16.dp) // Modifier digunakan di sini
        ) {
            ComponentDetailPendapatan(judul = "ID Kategori", isinya = pendapatan.idKategori.toString())
            Spacer(modifier = modifier.padding(4.dp)) // Modifier digunakan di sini
            ComponentDetailPendapatan(judul = "Total", isinya = pendapatan.total.toString())
            Spacer(modifier = modifier.padding(4.dp)) // Modifier digunakan di sini
            ComponentDetailPendapatan(judul = "Tanggal Transaksi", isinya = pendapatan.tanggalTransaksi)
            Spacer(modifier = modifier.padding(4.dp)) // Modifier digunakan di sini
            ComponentDetailPendapatan(judul = "Catatan", isinya = pendapatan.catatan)
        }
    }
}

@Composable
fun ComponentDetailPendapatan(
    modifier: Modifier = Modifier, // Parameter modifier digunakan
    judul: String,
    isinya: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
