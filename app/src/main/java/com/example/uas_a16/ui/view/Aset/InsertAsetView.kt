package com.example.uas_a16.ui.view.Aset



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_a16.ui.ViewModel.aset.InsertAsetViewModel
import com.example.uas_a16.ui.view.PenyediaModel

@Composable
fun InsertAsetScreen(
    navigateBack: () -> Unit,
    viewModel: InsertAsetViewModel = viewModel(factory = PenyediaModel.Factory)
) {
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.uiState.insertUiEvent.idAset,
            onValueChange = { idAset ->
                viewModel.updateInsertAsetState(viewModel.uiState.insertUiEvent.copy(idAset = idAset))
            },
            label = { Text("ID Aset") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.uiState.insertUiEvent.namaAset,
            onValueChange = { namaAset ->
                viewModel.updateInsertAsetState(viewModel.uiState.insertUiEvent.copy(namaAset = namaAset))
            },
            label = { Text("Nama Aset") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.uiState.insertUiEvent.idKategori,
            onValueChange = { idKategori ->
                viewModel.updateInsertAsetState(viewModel.uiState.insertUiEvent.copy(idKategori = idKategori))
            },
            label = { Text("ID Kategori") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (viewModel.uiState.insertUiEvent.idAset.isBlank() ||
                    viewModel.uiState.insertUiEvent.namaAset.isBlank() ||
                    viewModel.uiState.insertUiEvent.idKategori.isBlank()
                ) {
                    errorMessage = "Semua field harus diisi"
                } else {
                    viewModel.insertAset()
                    navigateBack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage!!,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}