package com.example.uas_a16.ui.view.pengeluaran

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import com.example.uas_a16.ui.ViewModel.pengeluaran.InsertPengeluaranEvent
import com.example.uas_a16.ui.ViewModel.pengeluaran.InsertPengeluaranUiState
import com.example.uas_a16.ui.ViewModel.pengeluaran.InsertPengeluaranViewModel
import com.example.uas_a16.ui.navigasi.CostumeTopAppBar
import kotlinx.coroutines.launch
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun FormInputPengeluaran(
    insertPengeluaranEvent: InsertPengeluaranEvent, // Wajib
    kategoriList: List<Kategori>, // Wajib
    asetList: List<Aset>, // Wajib
    onValueChange: (InsertPengeluaranEvent) -> Unit, // Wajib
    modifier: Modifier = Modifier, // Opsional (default: Modifier)
    enabled: Boolean = true // Opsional (default: true)
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
                value = insertPengeluaranEvent.idAset.toString(),
                onValueChange = { },
                label = { Text("Pilih Aset") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
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
                            onValueChange(insertPengeluaranEvent.copy(idAset = aset.idAset))
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
                value = insertPengeluaranEvent.idKategori.toString(),
                onValueChange = { },
                label = { Text("Pilih Kategori") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
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
                            onValueChange(insertPengeluaranEvent.copy(idKategori = kategori.idKategori))
                            expandedKategori = false
                        }
                    )
                }
            }
        }

        // Input lainnya...
    }
}
@Composable
fun InsertPengeluaranBody(
    insertPengeluaranUiState: InsertPengeluaranUiState,
    onValueChange: (InsertPengeluaranEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputPengeluaran(
            insertPengeluaranEvent = insertPengeluaranUiState.insertPengeluaranEvent,
            onValueChange = onValueChange,
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
fun FormInputPengeluaran(
    insertPengeluaranEvent: InsertPengeluaranEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertPengeluaranEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Dropdown untuk memilih aset
        OutlinedTextField(
            value = insertPengeluaranEvent.idAset.toString(),
            onValueChange = { onValueChange(insertPengeluaranEvent.copy(idAset = it.toInt())) },
            label = { Text("ID Aset") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Dropdown untuk memilih kategori
        OutlinedTextField(
            value = insertPengeluaranEvent.idKategori.toString(),
            onValueChange = { onValueChange(insertPengeluaranEvent.copy(idKategori = it.toInt())) },
            label = { Text("ID Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Input untuk total pengeluaran
        OutlinedTextField(
            value = insertPengeluaranEvent.total.toString(),
            onValueChange = { onValueChange(insertPengeluaranEvent.copy(total = it.toDouble())) },
            label = { Text("Total Pengeluaran") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Input untuk tanggal transaksi
        OutlinedTextField(
            value = insertPengeluaranEvent.tanggalTransaksi.toString(),
            onValueChange = { onValueChange(insertPengeluaranEvent.copy(tanggalTransaksi = "")) },
            label = { Text("Tanggal Transaksi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Input untuk catatan
        OutlinedTextField(
            value = insertPengeluaranEvent.catatan,
            onValueChange = { onValueChange(insertPengeluaranEvent.copy(catatan = it)) },
            label = { Text("Catatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }

        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}
