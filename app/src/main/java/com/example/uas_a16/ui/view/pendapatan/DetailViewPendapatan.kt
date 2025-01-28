package com.example.uas_a16.ui.view.pendapatan


import com.example.uas_a16.ui.navigasi.AlamatNavigasi



object DestinasiDetailPendapatan : AlamatNavigasi {
    override val route = "detailPendapatan"
    const val ID_ASET = "idAset"
    val routeWithArg = "$route/{$ID_ASET}"

    // Title untuk tampilan
    val titleRes = "Detail Pendapatan"
}
