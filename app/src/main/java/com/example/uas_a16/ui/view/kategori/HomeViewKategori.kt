package com.example.uas_a16.ui.view.kategori

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
import androidx.compose.material.icons.filled.Edit

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_a16.model.Aset
import com.example.uas_a16.ui.navigasi.CostumeTopAppBar

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import com.example.uas_a16.model.Kategori
import com.example.uas_a16.ui.ViewModel.kategori.HomeKategoriUiState
import com.example.uas_a16.ui.ViewModel.kategori.HomeKategoriViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeKategoriScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeKategoriViewModel = viewModel(),
    navigateToInsert: () -> Unit,
    navigateToDetail: (Kategori) -> Unit,  // Tambahkan parameter untuk detail
    navigateToEdit: (Kategori) -> Unit,  // Tambahkan parameter untuk edit
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {}
) {
    // Collecting state for kategori from the ViewModel
    val kategoriUiState by viewModel.kategoriUiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            CostumeTopAppBar(
                title = "Daftar Kategori",
                canNavigateBack = canNavigateBack,
                navigateUp = navigateUp,
                onRefresh = {
                    viewModel.getKategori()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToInsert) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Kategori")
            }
        }
    ) { innerPadding ->
        when (kategoriUiState) {
            is HomeKategoriUiState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    CircularProgressIndicator()
                }
            }
            is HomeKategoriUiState.Error -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(
                        text = "Terjadi kesalahan, coba lagi.",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            is HomeKategoriUiState.Success -> {
                val kategoriList = (kategoriUiState as HomeKategoriUiState.Success).kategori
                if (kategoriList.isEmpty()) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Text("Tidak ada data kategori.")
                    }
                } else {
                    KategoriList(
                        kategoriList = kategoriList,
                        onDeleteKategori = { kategori ->
                            viewModel.deleteKategori(kategori)
                        },
                        onEditKategori = { kategori ->
                            navigateToEdit(kategori)  // Navigasi ke edit
                        },
                        onDetailKategori = { kategori ->
                            navigateToDetail(kategori)  // Navigasi ke detail
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun KategoriList(
    kategoriList: List<Kategori>,
    onDeleteKategori: (Kategori) -> Unit,
    onEditKategori: (Kategori) -> Unit,  // Tambahkan parameter untuk edit
    onDetailKategori: (Kategori) -> Unit,  // Tambahkan parameter untuk detail
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        kategoriList.forEach { kategori ->
            KategoriItem(
                kategori = kategori,
                onDelete = { onDeleteKategori(kategori) },
                onEdit = { onEditKategori(kategori) },  // Kirim objek kategori untuk edit
                onDetail = { onDetailKategori(kategori) },  // Kirim objek kategori untuk detail
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}
@Composable
fun KategoriItem(
    kategori: Kategori,
    onDelete: () -> Unit,
    onEdit: () -> Unit,  // Tambahkan parameter untuk edit
    onDetail: () -> Unit,  // Tambahkan parameter untuk detail
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "ID Kategori: ${kategori.idKategori}")
                Text(text = "Nama Kategori: ${kategori.namaKategori}")
            }
            Row {
                // Icon untuk melihat detail
                IconButton(onClick = onDetail) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Detail Kategori")
                }
                // Icon untuk mengubah data
                IconButton(onClick = onEdit) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Kategori")
                }
                // Icon untuk menghapus data
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Hapus Kategori")
                }
            }
        }
    }
}