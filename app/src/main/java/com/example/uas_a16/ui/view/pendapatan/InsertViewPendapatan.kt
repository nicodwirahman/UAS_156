package com.example.uas_a16.ui.view.pendapatan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import com.example.uas_a16.model.Aset
import com.example.uas_a16.model.Kategori
import com.example.uas_a16.ui.ViewModel.pendapatan.InsertPendapatanEvent
import com.example.uas_a16.ui.ViewModel.pendapatan.InsertPendapatanUiState
import com.example.uas_a16.ui.ViewModel.pendapatan.InsertPendapatanViewModel
import com.example.uas_a16.ui.view.Aset.CostumeTopAppBar
import com.example.uas_a16.ui.view.PenyediaModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertPendapatanScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPendapatanViewModel = viewModel(factory = PenyediaModel.Factory),
    asetList: List<Aset>, // Tambahkan parameter asetList
    kategoriList: List<Kategori> // Tambahkan parameter kategoriList
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = "Input Pendapatan", // Ganti dengan judul sesuai kebutuhan
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        InsertPendapatanBody(
            insertPendapatanUiState = viewModel.uiState,
            onValueChange = viewModel::updateInsertPendapatanState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertPendapatan()
                    navigateBack()
                }
            },
            asetList = asetList, // Teruskan asetList
            kategoriList = kategoriList, // Teruskan kategoriList
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun InsertPendapatanBody(
    insertPendapatanUiState: InsertPendapatanUiState,
    onValueChange: (InsertPendapatanEvent) -> Unit,
    onSaveClick: () -> Unit,
    asetList: List<Aset>, // Tambahkan parameter asetList
    kategoriList: List<Kategori>, // Tambahkan parameter kategoriList
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputPendapatan(
            insertPendapatanEvent = insertPendapatanUiState.insertPendapatanEvent,
            onValueChange = onValueChange,
            asetList = asetList, // Teruskan asetList
            kategoriList = kategoriList, // Teruskan kategoriList
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputPendapatan(
    insertPendapatanEvent: InsertPendapatanEvent,
    kategoriList: List<Kategori>, // Daftar kategori
    asetList: List<Aset>, // Daftar aset
    onValueChange: (InsertPendapatanEvent) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true // Use enabled parameter here
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Dropdown untuk memilih aset
        var expandedAset by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expandedAset,
            onExpandedChange = { expandedAset = it }
        ) {
            OutlinedTextField(
                value = insertPendapatanEvent.idAset.toString(),
                onValueChange = { },
                label = { Text("Pilih Aset") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                enabled = enabled, // Set enabled here
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedAset)
                }
            )
            ExposedDropdownMenu(
                expanded = expandedAset,
                onDismissRequest = { expandedAset = false }
            ) {
                asetList.forEach { aset ->
                    DropdownMenuItem(
                        text = { Text(aset.namaAset) },
                        onClick = {
                            onValueChange(insertPendapatanEvent.copy(idAset = aset.idAset))
                            expandedAset = false
                        }
                    )
                }
            }
        }

        // Dropdown untuk memilih kategori
        var expandedKategori by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expandedKategori,
            onExpandedChange = { expandedKategori = it }
        ) {
            OutlinedTextField(
                value = insertPendapatanEvent.idKategori.toString(),
                onValueChange = { },
                label = { Text("Pilih Kategori") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                enabled = enabled, // Set enabled here
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedKategori)
                }
            )
            ExposedDropdownMenu(
                expanded = expandedKategori,
                onDismissRequest = { expandedKategori = false }
            ) {
                kategoriList.forEach { kategori ->
                    DropdownMenuItem(
                        text = { Text(kategori.namaKategori) },
                        onClick = {
                            onValueChange(insertPendapatanEvent.copy(idKategori = kategori.idKategori))
                            expandedKategori = false
                        }
                    )
                }
            }
        }
    }
}
