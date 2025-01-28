package com.example.uas_a16.ui.view.pendapatan



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_a16.ui.ViewModel.pendapatan.InsertPendapatanEvent
import com.example.uas_a16.ui.ViewModel.pendapatan.InsertPendapatanUiState
import com.example.uas_a16.ui.ViewModel.pendapatan.UpdatePendapatanUiState
import com.example.uas_a16.ui.ViewModel.pendapatan.UpdatePendapatanViewModel
import com.example.uas_a16.ui.view.Aset.CostumeTopAppBar
import com.example.uas_a16.ui.view.PenyediaModel
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePendapatanScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdatePendapatanViewModel = viewModel(factory = PenyediaModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    // Ambil state langsung dari ViewModel
    val uiState = viewModel.updatePendapatanUiState.value

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = "Update Pendapatan",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack
            )
        }
    ) { innerPadding ->
        when (uiState) {
            is UpdatePendapatanUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
            is UpdatePendapatanUiState.Success -> {
                EntryBody(
                    insertUiState = viewModel.insertUiState,
                    onPendapatanValueChange = viewModel::updateInsertPendapatanState,
                    onSaveClick = {
                        coroutineScope.launch {
                            viewModel.updatePendapatan()
                            onNavigate()
                        }
                    },
                    modifier = Modifier
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                        .fillMaxWidth()
                )
            }
            is UpdatePendapatanUiState.Error -> {
                Text(
                    text = "Gagal memperbarui data. Silakan coba lagi.",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
@Composable
fun EntryBody(
    insertUiState: InsertPendapatanUiState,
    onPendapatanValueChange: (InsertPendapatanEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertUiEvent = insertUiState.insertPendapatanEvent,
            onValueChange = onPendapatanValueChange,
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
fun FormInput(
    insertUiEvent: InsertPendapatanEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertPendapatanEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.idKategori.toString(),
            onValueChange = { onValueChange(insertUiEvent.copy(idKategori = it.toIntOrNull() ?: 0)) },
            label = { Text("ID Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertUiEvent.total.toString(),
            onValueChange = { onValueChange(insertUiEvent.copy(total = it.toDoubleOrNull() ?: 0.0)) },
            label = { Text("Total Pendapatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertUiEvent.tanggalTransaksi,
            onValueChange = { onValueChange(insertUiEvent.copy(tanggalTransaksi = it)) },
            label = { Text("Tanggal Transaksi (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertUiEvent.catatan,
            onValueChange = { onValueChange(insertUiEvent.copy(catatan = it)) },
            label = { Text("Catatan") },
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