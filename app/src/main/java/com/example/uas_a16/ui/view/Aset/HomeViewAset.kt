package com.example.uas_a16.ui.view.Aset

import HomeAsetViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.uas_a16.ui.navigasi.CostumeTopAppBar
import kotlinx.coroutines.launch


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.uas_a16.Repository.AsetRepository
import com.example.uas_a16.model.Aset
import com.example.uas_a16.ui.view.PenyediaModel

// Main Screen
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeAsetScreen(
    navigateToItemEntry: () -> Unit,
    navigateToDetail: (String) -> Unit,
    navigateToEdit: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeAsetViewModel = viewModel(factory = PenyediaModel.Factory)
) {
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = "Home Aset",
                canNavigateBack = false,
                onRefresh = { viewModel.getAset() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToItemEntry) {
                Icon(Icons.Default.Add, contentDescription = "Add Aset")
            }
        }
    ) { innerPadding ->
        when (viewModel.asetUiState) {
            is HomeAsetUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is HomeAsetUiState.Success -> {
                val asetList = (viewModel.asetUiState as HomeAsetUiState.Success).aset
                if (asetList.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Tidak ada data Aset")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.padding(innerPadding),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(asetList.size) { index ->
                            val item = asetList[index]
                            AsetItem(
                                aset = item,
                                onDetailClick = { navigateToDetail(item.idAset.toString()) },
                                onEditClick = { navigateToEdit(item.idAset.toString()) },
                                onDeleteClick = {
                                    viewModel.deleteAset(item)
                                    viewModel.getAset()
                                }
                            )
                        }
                    }
                }
            }
            is HomeAsetUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Terjadi kesalahan saat memuat data.")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.getAset() }) {
                            Text("Coba Lagi")
                        }
                    }
                }
            }

            else -> {}
        }
    }
}// Komponen untuk menampilkan item Aset
@Composable
fun AsetItem(
    aset: Aset,
    onDetailClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = aset.namaAset, style = MaterialTheme.typography.titleMedium)
                Text(text = "ID Kategori: ${aset.idKategori}", style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = onDetailClick) {
                Icon(Icons.Default.Info, contentDescription = "Detail")
            }
            IconButton(onClick = onEditClick) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Default.Delete, contentDescription = "Hapus")
            }
        }
    }
}