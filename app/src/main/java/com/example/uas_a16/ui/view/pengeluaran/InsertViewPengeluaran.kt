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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_a16.Repository.PengeluaranRepository
import com.example.uas_a16.model.Aset
import com.example.uas_a16.model.Kategori
import com.example.uas_a16.model.Pengeluaran
import com.example.uas_a16.ui.ViewModel.pengeluaran.InsertPengeluaranEvent
import com.example.uas_a16.ui.ViewModel.pengeluaran.InsertPengeluaranUiState
import com.example.uas_a16.ui.ViewModel.pengeluaran.InsertPengeluaranViewModel
import com.example.uas_a16.ui.navigasi.CostumeTopAppBar
import kotlinx.coroutines.launch
import java.util.Date
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

class InsertPengeluaranViewModel(private val pengeluaranRepository: PengeluaranRepository) : ViewModel() {

    var uiState by mutableStateOf(InsertPengeluaranUiState())
        private set

    fun updateInsertPengeluaranState(insertPengeluaranEvent: InsertPengeluaranEvent) {
        uiState = uiState.copy(insertPengeluaranEvent = insertPengeluaranEvent)
    }

    fun insertPengeluaran() {
        viewModelScope.launch {
            try {
                val pengeluaran = uiState.insertPengeluaranEvent.toPengeluaran()
                pengeluaranRepository.insertPengeluaran(pengeluaran)
                uiState = uiState.copy(isSuccess = true, successMessage = "Pengeluaran berhasil disimpan")
            } catch (e: Exception) {
                uiState = uiState.copy(isError = true, errorMessage = e.message ?: "Gagal menyimpan pengeluaran")
            }
        }
    }
}

data class InsertPengeluaranUiState(
    val insertPengeluaranEvent: InsertPengeluaranEvent = InsertPengeluaranEvent(),
    val isSuccess: Boolean = false,
    val successMessage: String = "",
    val isError: Boolean = false,
    val errorMessage: String = ""
)

data class InsertPengeluaranEvent(
    val idAset: Int = 0,
    val idKategori: Int = 0,
    val total: Double = 0.0,
    val tanggalTransaksi: String = "",
    val catatan: String = ""
)

fun InsertPengeluaranEvent.toPengeluaran(): Pengeluaran = Pengeluaran(
    idAset = idAset,
    idKategori = idKategori,
    total = total,
    tanggalTransaksi = tanggalTransaksi,
    catatan = catatan
)
