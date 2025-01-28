package com.example.uas_a16.ui.view.kategori

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_a16.model.Kategori
import com.example.uas_a16.ui.ViewModel.aset.UpdateAsetViewModel
import com.example.uas_a16.ui.ViewModel.aset.UpdateUiEvent
import com.example.uas_a16.ui.ViewModel.aset.UpdateUiState
import com.example.uas_a16.ui.ViewModel.kategori.UpdateKategoriViewModel
import com.example.uas_a16.ui.view.Aset.CostumeTopAppBar
import com.example.uas_a16.ui.view.PenyediaModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateAsetScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateAsetViewModel = viewModel(factory = PenyediaModel.Factory),
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = "Update Aset", // Title for Update Aset
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack
            )
        }
    ) { innerPadding ->
        UpdateAsetBody(
            updateUiState = viewModel.uiState,
            onAsetValueChange = viewModel::updateAsetState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateAset(
                        onSuccess = {
                            onNavigate()
                        },
                        onError = { message ->
                            // Handle error if needed
                        }
                    )
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun UpdateAsetBody(
    updateUiState: UpdateUiState,
    onAsetValueChange: (UpdateUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputAset(
            updateUiEvent = updateUiState.updateUiEvent,
            onValueChange = onAsetValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@Composable
fun FormInputAset(
    updateUiEvent: UpdateUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (UpdateUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = updateUiEvent.idAset,
            onValueChange = { onValueChange(updateUiEvent.copy(idAset = it)) },
            label = { Text("ID Aset") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false, // ID Aset cannot be changed
            singleLine = true
        )

        OutlinedTextField(
            value = updateUiEvent.namaAset,
            onValueChange = { onValueChange(updateUiEvent.copy(namaAset = it)) },
            label = { Text("Nama Aset") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = updateUiEvent.idKategori,
            onValueChange = { onValueChange(updateUiEvent.copy(idKategori = it)) },
            label = { Text("ID Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled) {
            Text(
                text = "Isi semua data dengan benar!",
                modifier = Modifier.padding(12.dp)
            )
        }

        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}
