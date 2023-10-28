package com.taufik.financialportofolio.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Data(
    val dataX: List<DataX> = listOf(),
    val label: String = "", // Tarik Tunai
    val percentage: String = "" // 55
): Parcelable