package com.example.uas_a16.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uas_a16.ui.ViewModel.Keuangan.FinanceViewModel
import com.example.uas_a16.ui.ViewModel.pengeluaran.InsertPengeluaranEvent
import com.example.uas_a16.ui.view.Aset.DetailAsetScreen
import com.example.uas_a16.ui.view.Aset.HomeAsetScreen
import com.example.uas_a16.ui.view.Aset.InsertAsetScreen
import com.example.uas_a16.ui.view.Aset.UpdateAsetScreen
import com.example.uas_a16.ui.view.Keungan.HomeScreen
import com.example.uas_a16.ui.view.PenyediaModel
import com.example.uas_a16.ui.view.kategori.HomeKategoriScreen
import com.example.uas_a16.ui.view.kategori.InsertKategoriScreen
import com.example.uas_a16.ui.view.pendapatan.DetailPendapatanScreen
import com.example.uas_a16.ui.view.pendapatan.HomePendapatanScreen
import com.example.uas_a16.ui.view.pendapatan.InsertPendapatanScreen
import com.example.uas_a16.ui.view.pendapatan.UpdatePendapatanScreen
import com.example.uas_a16.ui.view.pengeluaran.DetailPengeluaranScreen
import com.example.uas_a16.ui.view.pengeluaran.FormInputPengeluaran
import com.example.uas_a16.ui.view.pengeluaran.HomePengeluaranScreen
import com.example.uas_a16.ui.view.pengeluaran.UpdateScreen


@Composable
fun PengelolaHalaman(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: FinanceViewModel = viewModel(factory = PenyediaModel.Factory)

    NavHost(
        navController = navController,
        startDestination = "homeScreen" // Mengubah start destination ke HomeScreen
    ) {
        composable("homeScreen") {
            HomeScreen(viewModel = viewModel)
        }

        composable("homePengeluaran") {
            HomePengeluaranScreen(
                navigateToInsert = { navController.navigate("insertPengeluaran") },
                navigateToDetail = { idAset ->
                    navController.navigate("${DestinasiDetailPengeluaran.route}/$idAset")
                }
            )
        }

        composable("insertPengeluaran") {
            FormInputPengeluaran(
                insertPengeluaranEvent = InsertPengeluaranEvent(),
                kategoriList = emptyList(),
                asetList = emptyList(),
                onValueChange = {},
                modifier = modifier,
                enabled = true
            )
        }

        composable("updatePengeluaran") {
            UpdateScreen(
                onBack = { navController.popBackStack() },
                onNavigate = {}
            )
        }

        composable(DestinasiDetailPengeluaran.routeWithArg) { backStackEntry ->
            val idAset = backStackEntry.arguments?.getString(DestinasiDetailPengeluaran.ID_ASET)
            idAset?.let {
                DetailPengeluaranScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEdit = {},
                    onDelete = {}
                )
            }
        }

        composable("homePendapatan") {
            HomePendapatanScreen(
                navigateToInsert = { navController.navigate("insertPendapatan") },
                onDetailPendapatan = { idAset ->
                    navController.navigate("${DestinasiDetailPendapatan.route}/$idAset")
                }
            )
        }

        composable("insertPendapatan") {
            InsertPendapatanScreen(
                navigateBack = { navController.popBackStack() },
                asetList = emptyList(),
                kategoriList = emptyList()
            )
        }

        composable("updateAset") {
            UpdateAsetScreen(
                onBack = { navController.popBackStack() },
                onNavigate = {}
            )
        }

        composable("insertKategori") {
            InsertKategoriScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable("homeKategori") {
            HomeKategoriScreen(
                navigateToInsert = { navController.navigate("insertKategori") },
                navigateToDetail = {},
                navigateToEdit = {}
            )
        }

        composable("insertAset") {
            InsertAsetScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

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

        composable(DestinasiDetailAset.routeWithArg) { backStackEntry ->
            val idAset = backStackEntry.arguments?.getString(DestinasiDetailAset.ID_ASET)
            idAset?.let {
                DetailAsetScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEdit = {}
                )
            }
        }

        composable(DestinasiDetailPendapatan.routeWithArg) { backStackEntry ->
            val idAset = backStackEntry.arguments?.getString(DestinasiDetailPendapatan.ID_ASET)
            idAset?.let {
                DetailPendapatanScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEdit = {},
                    onDelete = {}
                )
            }
        }
    }
}