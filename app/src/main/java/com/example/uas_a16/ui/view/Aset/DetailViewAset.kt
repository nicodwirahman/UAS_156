package com.example.uas_a16.ui.view.Aset


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.uas_a16.model.Aset
import com.example.uas_a16.ui.ViewModel.aset.DetailViewModelAset

import com.example.uas_a16.ui.ViewModel.aset.DetailUiState
import com.example.uas_a16.ui.ViewModel.aset.toAset
import com.example.uas_a16.ui.navigasi.AlamatNavigasi

object DestinasiDetailAset : AlamatNavigasi {
    override val route = "detailAset"
    const val ID_ASET = "idAset"
    val routeWithArg = "$route/{$ID_ASET}"

    // Menambahkan titleRes
    val titleRes = "Detail Aset"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAsetScreen(
    navigateBack: () -> Unit,
    navigateToEdit: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModelAset = viewModel()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailAset.titleRes, // Menggunakan title dari DestinasiDetailAset
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToEdit,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Aset"
                )
            }
        }
    ) { innerPadding ->
        BodyDetailAset(
            detailUiState = viewModel.detailUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyDetailAset(
    detailUiState: DetailUiState,
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
                ItemDetailAset(
                    aset = detailUiState.detailUiEvent.toAset(),
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ItemDetailAset(
    modifier: Modifier = Modifier,
    aset: Aset
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
            ComponentDetailAset(judul = "ID Aset", isinya = aset.idAset.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailAset(judul = "Nama Aset", isinya = aset.namaAset)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailAset(judul = "ID Kategori", isinya = aset.idKategori.toString())
        }
    }
}

@Composable
fun ComponentDetailAset(
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