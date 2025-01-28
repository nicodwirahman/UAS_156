package com.example.uas_a16.ui.view.pendapatan


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_a16.model.Pendapatan
import com.example.uas_a16.ui.ViewModel.pendapatan.DetailPendapatanViewModel
import com.example.uas_a16.ui.ViewModel.pendapatan.DetailPendapatanUiState
import com.example.uas_a16.ui.ViewModel.pendapatan.toPendapatan
import com.example.uas_a16.ui.navigasi.AlamatNavigasi
import com.example.uas_a16.ui.navigasi.CostumeTopAppBar


object DestinasiDetailPendapatan : AlamatNavigasi {
    override val route = "detailPendapatan"
    const val ID_ASET = "idAset"
    val routeWithArg = "$route/{$ID_ASET}"

    // Title untuk tampilan
    val titleRes = "Detail Pendapatan"
}

@Composable
fun ComponentDetailPendapatan(
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
