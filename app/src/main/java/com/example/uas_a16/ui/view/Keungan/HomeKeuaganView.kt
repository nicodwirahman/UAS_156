package com.example.uas_a16.ui.view.Keungan



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_a16.ui.ViewModel.Keuangan.HomePengeluaranViewModel

import androidx.compose.runtime.Composable


import androidx.navigation.NavController


@Composable
fun HomeKeuanganView(viewModel: HomePengeluaranViewModel = viewModel(), navController: NavController) {
    val saldo by viewModel.saldo.observeAsState(0.0)
    val totalPendapatan by viewModel.totalPendapatan.observeAsState(0.0)
    val totalPengeluaran by viewModel.totalPengeluaran.observeAsState(0.0)
    val saldoColor by viewModel.saldoColor.observeAsState(Color.Gray)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Box untuk menampilkan saldo
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Saldo",
                )
                Text(
                    text = "Rp ${saldo}",
                    color = saldoColor, // Menambahkan warna saldo
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        // Menampilkan total pengeluaran
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Total Pengeluaran",
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Rp ${totalPengeluaran}",
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menampilkan total pendapatan
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Total Pendapatan",
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Rp ${totalPendapatan}",
            )
        }

        // Button untuk refresh data (misalnya untuk mendapatkan data terbaru)
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { /* Handle refresh action */ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Segarkan Data")
        }
    }
}

@Composable
fun HomeKeuanganView(viewModel: HomePengeluaranViewModel = viewModel()) {
    val saldo by viewModel.saldo.observeAsState(0.0)
    val totalPendapatan by viewModel.totalPendapatan.observeAsState(0.0)
    val totalPengeluaran by viewModel.totalPengeluaran.observeAsState(0.0)
    val saldoColor by viewModel.saldoColor.observeAsState(Color.Gray)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Box untuk menampilkan saldo
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Saldo",

                )
                Text(
                    text = "Rp ${saldo}",

                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        // Menampilkan total pengeluaran
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Total Pengeluaran",

                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Rp ${totalPengeluaran}",

            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menampilkan total pendapatan
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Total Pendapatan",

                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Rp ${totalPendapatan}",

            )
        }

        // Button untuk refresh data (misalnya untuk mendapatkan data terbaru)
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { /* Handle refresh action */ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Segarkan Data")
        }
    }
}