package com.example.uas_a16.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uas_a16.ui.ViewModel.pengeluaran.InsertPengeluaranEvent
import com.example.uas_a16.ui.view.Aset.DetailAsetScreen
import com.example.uas_a16.ui.view.Aset.HomeAsetScreen
import com.example.uas_a16.ui.view.Aset.InsertAsetScreen
import com.example.uas_a16.ui.view.Aset.UpdateAsetScreen
import com.example.uas_a16.ui.view.kategori.HomeKategoriScreen
import com.example.uas_a16.ui.view.kategori.InsertKategoriScreen
import com.example.uas_a16.ui.view.pendapatan.DestinasiDetailPendapatan
import com.example.uas_a16.ui.view.pendapatan.DetailPendapatanScreen
import com.example.uas_a16.ui.view.pendapatan.HomePendapatanScreen
import com.example.uas_a16.ui.view.pendapatan.InsertPendapatanScreen
import com.example.uas_a16.ui.view.pengeluaran.DetailPengeluaranScreen
import com.example.uas_a16.ui.view.pengeluaran.FormInputPengeluaran
import com.example.uas_a16.ui.view.pengeluaran.HomePengeluaranScreen
import com.example.uas_a16.ui.view.pengeluaran.UpdateScreen


@Composable
fun PengelolaHalaman() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DestinasiDetailPengeluaran.route
    ) {
        // Rute untuk Detail Pengeluaran
        composable(DestinasiDetailPengeluaran.routeWithArg) { backStackEntry ->
            val idAset = backStackEntry.arguments?.getString(DestinasiDetailPengeluaran.ID_ASET)
            DetailPengeluaranScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEdit = { /* Navigasi ke layar edit pengeluaran */ },
                onDelete = { /* Handle delete action */ }
            )
        }

        // Rute untuk Home Pengeluaran
        composable("homePengeluaran") {
            HomePengeluaranScreen(
                navigateToInsert = { navController.navigate("insertPengeluaran") },
                navigateToDetail = { idAset ->
                    navController.navigate("${DestinasiDetailPengeluaran.route}/$idAset")
                }
            )
        }

        // Rute untuk Insert Pengeluaran
        composable("insertPengeluaran") {
            FormInputPengeluaran(
                insertPengeluaranEvent = InsertPengeluaranEvent(),
                kategoriList = listOf(), // Ganti dengan daftar kategori yang valid
                asetList = listOf(), // Ganti dengan daftar aset yang valid
                onValueChange = { /* Handle value change */ },
                modifier = Modifier, // Opsional, bisa dihapus jika tidak diperlukan
                enabled = true // Opsional, bisa dihapus jika tidak diperlukan
            )
        }
        // Rute untuk Update Pengeluaran
        composable("updatePengeluaran") {
            UpdateScreen(
                onBack = { navController.popBackStack() },
                onNavigate = { /* Handle navigate action */ }
            )
        }

        // Rute untuk Detail Pendapatan
        composable(DestinasiDetailPendapatan.routeWithArg) { backStackEntry ->
            val idAset = backStackEntry.arguments?.getString(DestinasiDetailPendapatan.ID_ASET)
            DetailPendapatanScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEdit = { /* Navigasi ke layar edit pendapatan */ },
                onDelete = { /* Handle delete action */ }
            )
        }

        // Rute untuk Home Pendapatan
        composable("homePendapatan") {
            HomePendapatanScreen(
                navigateToInsert = { navController.navigate("insertPendapatan") },
                onDetailPendapatan = { idAset ->
                    navController.navigate("${DestinasiDetailPendapatan.route}/$idAset")
                }
            )
        }

        // Rute untuk Insert Pendapatan
        composable("insertPendapatan") {
            InsertPendapatanScreen(
                navigateBack = { navController.popBackStack() },
                asetList = listOf(), // Isi dengan daftar aset
                kategoriList = listOf() // Isi dengan daftar kategori
            )
        }

        // Rute untuk Update Aset
        composable("updateAset") {
            UpdateAsetScreen(
                onBack = { navController.popBackStack() },
                onNavigate = { /* Handle navigate action */ }
            )
        }

        // Rute untuk Insert Kategori
        composable("insertKategori") {
            InsertKategoriScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        // Rute untuk Home Kategori
        composable("homeKategori") {
            HomeKategoriScreen(
                navigateToInsert = { navController.navigate("insertKategori") },
                navigateToDetail = { kategori -> /* Handle detail kategori */ },
                navigateToEdit = { kategori -> /* Handle edit kategori */ }
            )
        }

        // Rute untuk Insert Aset
        composable("insertAset") {
            InsertAsetScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        // Rute untuk Home Aset
        composable("homeAset") {
            HomeAsetScreen(
                navigateToItemEntry = { navController.navigate("insertAset") },
                navigateToDetail = { idAset ->
                    navController.navigate("${DestinasiDetailAset.route}/$idAset")
                },
                navigateToEdit = { idAset ->
                    navController.navigate("updateAset/$idAset")
                }
            )
        }

        // Rute untuk Detail Aset
        composable(DestinasiDetailAset.routeWithArg) { backStackEntry ->
            val idAset = backStackEntry.arguments?.getString(DestinasiDetailAset.ID_ASET)
            DetailAsetScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEdit = { /* Navigasi ke layar edit aset */ }
            )
        }
    }
}