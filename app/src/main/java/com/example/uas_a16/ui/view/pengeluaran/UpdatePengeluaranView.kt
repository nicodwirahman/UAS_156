package com.example.uas_a16.ui.view.pengeluaran

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uas_a16.ui.ViewModel.pengeluaran.InsertPengeluaranEvent
import com.example.uas_a16.ui.ViewModel.pengeluaran.InsertPengeluaranUiState


@Composable
fun EntryBody(
    insertUiState: InsertPengeluaranUiState,
    onPengeluaranValueChange: (InsertPengeluaranEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertUiEvent = insertUiState.insertPengeluaranEvent,
            onValueChange = onPengeluaranValueChange,
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
    insertUiEvent: InsertPengeluaranEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertPengeluaranEvent) -> Unit = {},
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
            label = { Text("Total Pengeluaran") },
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
