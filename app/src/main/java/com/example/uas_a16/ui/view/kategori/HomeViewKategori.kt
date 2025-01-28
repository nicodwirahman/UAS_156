package com.example.uas_a16.ui.view.kategori

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uas_a16.model.Kategori


@Composable
fun KategoriItem(
    kategori: Kategori,
    onDelete: () -> Unit,
    onEdit: () -> Unit,  // Tambahkan parameter untuk edit
    onDetail: () -> Unit,  // Tambahkan parameter untuk detail
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "ID Kategori: ${kategori.idKategori}")
                Text(text = "Nama Kategori: ${kategori.namaKategori}")
            }
            Row {
                // Icon untuk melihat detail
                IconButton(onClick = onDetail) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Detail Kategori")
                }
                // Icon untuk mengubah data
                IconButton(onClick = onEdit) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Kategori")
                }
                // Icon untuk menghapus data
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Hapus Kategori")
                }
            }
        }
    }
}