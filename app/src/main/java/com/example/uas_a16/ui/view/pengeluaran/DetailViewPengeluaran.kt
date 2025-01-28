package com.example.uas_a16.ui.view.pengeluaran

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_a16.model.Pengeluaran
import com.example.uas_a16.ui.ViewModel.pengeluaran.DetailPengeluaranUiState
import com.example.uas_a16.ui.ViewModel.pengeluaran.DetailPengeluaranViewModel
import com.example.uas_a16.ui.ViewModel.pengeluaran.toPengeluaran
import com.example.uas_a16.ui.navigasi.AlamatNavigasi
import com.example.uas_a16.ui.navigasi.CostumeTopAppBar

object DestinasiDetailPengeluaran: AlamatNavigasi {
    override val route = "detailPengeluaran"
    const val ID_ASET = "idAset"
    val routeWithArg = "$route/{$ID_ASET}"

    // Menambahkan titleRes
    val titleRes = "Detail Pengeluaran"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPengeluaranScreen(
    navigateBack: () -> Unit,
    navigateToEdit: () -> Unit,
    onDelete: () -> Unit, // Tambahkan parameter untuk fungsi hapus
    modifier: Modifier = Modifier,
    viewModel: DetailPengeluaranViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailPengeluaran.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            Row(
                modifier = Modifier.padding(18.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FloatingActionButton(
                    onClick = navigateToEdit,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Pengeluaran")
                }
                FloatingActionButton(
                    onClick = onDelete, // Button hapus
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.errorContainer
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Hapus Pengeluaran")
                }
            }
        }
    ) { innerPadding ->
        BodyDetailPengeluaran(
            detailUiState = viewModel.detailUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
@Composable
fun BodyDetailPengeluaran(
    detailUiState: DetailPengeluaranUiState,
    modifier: Modifier = Modifier
) {
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailUiState.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailUiState.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailUiState.isUiEventNotEmpty -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailPengeluaran(
                    pengeluaran = detailUiState.detailUiEvent.toPengeluaran(),
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ItemDetailPengeluaran(
    modifier: Modifier = Modifier,
    pengeluaran: Pengeluaran
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(top = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            ComponentDetailPengeluaran(judul = "ID Kategori", isinya = pengeluaran.idKategori.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPengeluaran(judul = "Total", isinya = pengeluaran.total.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPengeluaran(judul = "Tanggal Transaksi", isinya = pengeluaran.tanggalTransaksi)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailPengeluaran(judul = "Catatan", isinya = pengeluaran.catatan)
        }
    }
}

@Composable
fun ComponentDetailPengeluaran(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}