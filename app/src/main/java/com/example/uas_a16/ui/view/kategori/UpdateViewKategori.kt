package com.example.uas_a16.ui.view.kategori

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uas_a16.ui.ViewModel.aset.UpdateUiEvent

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
