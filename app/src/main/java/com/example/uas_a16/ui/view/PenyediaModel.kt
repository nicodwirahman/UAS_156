package com.example.uas_a16.ui.view

import HomeAsetViewModel
import android.text.Spannable.Factory
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uas_a16.Application.PencatatanApp
import com.example.uas_a16.ui.ViewModel.aset.DetailViewModelAset
import com.example.uas_a16.ui.ViewModel.aset.InsertAsetViewModel
import com.example.uas_a16.ui.ViewModel.aset.UpdateAsetViewModel
import com.example.uas_a16.ui.ViewModel.kategori.HomeKategoriViewModel
import com.example.uas_a16.ui.ViewModel.kategori.InsertKategoriViewModel
import com.example.uas_a16.ui.ViewModel.kategori.UpdateKategoriViewModel
import com.example.uas_a16.ui.ViewModel.pendapatan.DetailPendapatanViewModel
import com.example.uas_a16.ui.ViewModel.pendapatan.HomePendapatanViewModel
import com.example.uas_a16.ui.ViewModel.pendapatan.InsertPendapatanViewModel
import com.example.uas_a16.ui.ViewModel.pendapatan.UpdatePendapatanViewModel
import com.example.uas_a16.ui.ViewModel.pengeluaran.DetailPengeluaranViewModel
import com.example.uas_a16.ui.ViewModel.pengeluaran.HomePengeluaranViewModel
import com.example.uas_a16.ui.ViewModel.pengeluaran.InsertPengeluaranViewModel
import com.example.uas_a16.ui.ViewModel.pengeluaran.UpdatePengeluaranViewModel

object PenyediaModel {
    val Factory = viewModelFactory {
        // Aset ViewModels
        initializer {
            InsertAsetViewModel(
                PencatatanApp().container.asetRepository
            )
        }
        initializer {
            HomeAsetViewModel(
                PencatatanApp().container.asetRepository
            )
        }
        initializer {
            UpdateAsetViewModel(
                PencatatanApp().container.asetRepository
            )
        }
        initializer {
            DetailViewModelAset(
                PencatatanApp().container.asetRepository
            )
        }

        // Pengeluaran ViewModels
        initializer {
            InsertPengeluaranViewModel(
                PencatatanApp().container.pengeluaranRepository
            )
        }
        initializer {
            HomePengeluaranViewModel(
                PencatatanApp().container.pengeluaranRepository
            )
        }
        initializer {
            UpdatePengeluaranViewModel(
                PencatatanApp().container.pengeluaranRepository
            )
        }
        initializer {
            DetailPengeluaranViewModel(
                PencatatanApp().container.pengeluaranRepository
            )
        }

        // Pendapatan ViewModels
        initializer {
            InsertPendapatanViewModel(
                PencatatanApp().container.pendapatanRepository
            )
        }
        initializer {
            HomePendapatanViewModel(
                PencatatanApp().container.pendapatanRepository
            )
        }
        initializer {
            UpdatePendapatanViewModel(
                PencatatanApp().container.pendapatanRepository
            )
        }
        initializer {
            DetailPendapatanViewModel(
                PencatatanApp().container.pendapatanRepository
            )
        }

        // Kategori ViewModels
        initializer {
            InsertKategoriViewModel(
                PencatatanApp().container.kategoriRepository
            )
        }
        initializer {
            HomeKategoriViewModel(
                PencatatanApp().container.kategoriRepository
            )
        }
        initializer {
            UpdateKategoriViewModel(
                PencatatanApp().container.kategoriRepository
            )
        }
        initializer {
            DetailKategoriViewMode(
                PencatatanApp().container.kategoriRepository
            )
        }
    }
}
