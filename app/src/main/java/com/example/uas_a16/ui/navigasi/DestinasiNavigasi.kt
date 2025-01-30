package com.example.uas_a16.ui.navigasi

interface AlamatNavigasi {
    val route: String
}

object DestinasiKeuangan : AlamatNavigasi {
    override val route = "keuangan"
}

object DestinasiPendapatan : AlamatNavigasi {
    override val route = "pendapatan"
}

object DestinasiPengeluaran : AlamatNavigasi {
    override val route = "pengeluaran"
}

object DestinasiInsert : AlamatNavigasi {
    override val route = "insert"
}

object DestinasiUpdate : AlamatNavigasi {
    override val route = "update"
    const val Kode = "Kode"
    val routeWithArg = "$route/{$Kode}"
}

object DestinasiDetailPendapatan : AlamatNavigasi {
    override val route = "detailPendapatan"
    const val ID_ASET = "idAset"
    val routeWithArg = "$route/{$ID_ASET}"
    val titleRes = "Detail Pendapatan"
}

object DestinasiDetailPengeluaran : AlamatNavigasi {
    override val route = "detailPengeluaran"
    const val ID_ASET = "idAset"
    val routeWithArg = "$route/{$ID_ASET}"
}

object DestinasiDetailKategori : AlamatNavigasi {
    override val route = "detailKategori"
    const val idKategori = "idKategori"
    val routeWithArg = "$route/{$idKategori}"
}

object DestinasiDetailAset : AlamatNavigasi {
    override val route = "detailAset"
    const val ID_ASET = "idAset"
    val routeWithArg = "$route/{$ID_ASET}"
    val titleRes = "Detail Aset"
}
