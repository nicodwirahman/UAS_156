package com.example.uas_a16.ui.view.pengeluaran

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Button

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.uas_a16.Repository.PengeluaranRepository
import com.example.uas_a16.model.Aset
import com.example.uas_a16.model.Kategori
import com.example.uas_a16.model.Pengeluaran
import com.example.uas_a16.ui.ViewModel.pengeluaran.InsertPengeluaranEvent
import com.example.uas_a16.ui.ViewModel.pengeluaran.InsertPengeluaranUiState

import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputPengeluaran(
    insertPengeluaranEvent: InsertPengeluaranEvent,
    kategoriList: List<Kategori>,
    asetList: List<Aset>,
    onValueChange: (InsertPengeluaranEvent) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
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
                value = asetList.find { it.idAset == insertPengeluaranEvent.idAset }?.namaAset ?: "Pilih Aset",
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
                value = kategoriList.find { it.idKategori == insertPengeluaranEvent.idKategori }?.namaKategori ?: "Pilih Kategori",
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

        // Input untuk total pengeluaran
        OutlinedTextField(
            value = if (insertPengeluaranEvent.total > 0) insertPengeluaranEvent.total.toString() else "",
            onValueChange = { onValueChange(insertPengeluaranEvent.copy(total = it.toDoubleOrNull() ?: 0.0)) },
            label = { Text("Total Pengeluaran") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Input untuk tanggal transaksi
        OutlinedTextField(
            value = insertPengeluaranEvent.tanggalTransaksi,
            onValueChange = { onValueChange(insertPengeluaranEvent.copy(tanggalTransaksi = it)) },
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
            kategoriList = emptyList(),
            asetList = emptyList(),
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

