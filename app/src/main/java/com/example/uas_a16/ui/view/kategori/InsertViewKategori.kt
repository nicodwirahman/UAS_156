package com.example.uas_a16.ui.view.kategori

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_a16.model.Kategori
import com.example.uas_a16.ui.ViewModel.kategori.InsertKategoriUiState
import com.example.uas_a16.ui.ViewModel.kategori.InsertKategoriViewModel
import com.example.uas_a16.ui.view.Aset.CostumeTopAppBar
import com.example.uas_a16.ui.view.PenyediaModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertKategoriScreen(
    modifier: Modifier = Modifier,
    viewModel: InsertKategoriViewModel = viewModel(factory = PenyediaModel.Factory),
    navigateBack: () -> Unit
) {
    val insertKategoriUiState by viewModel.insertKategoriUiState.collectAsState()

    // Form input untuk kategori
    var kategoriName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = "Tambah Kategori",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Validasi input
                if (kategoriName.isNotEmpty()) {
                    viewModel.insertKategori(Kategori(namaKategori = kategoriName))
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Kategori")
            }
        }
    ) { innerPadding ->
        when (insertKategoriUiState) {
            is InsertKategoriUiState.Loading -> {
                // Menampilkan loading indicator
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    CircularProgressIndicator()
                }
            }
            is InsertKategoriUiState.Error -> {
                // Menampilkan pesan error jika terjadi kesalahan saat insert
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text("Terjadi kesalahan, coba lagi.", color = MaterialTheme.colorScheme.error)
                }
            }
            is InsertKategoriUiState.Success -> {
                // Menampilkan pesan sukses setelah kategori berhasil ditambahkan
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text("Kategori berhasil ditambahkan!")
                }
            }
        }

        // Form input kategori
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TextField(
                value = kategoriName,
                onValueChange = { kategoriName = it },
                label = { Text("Nama Kategori") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}
