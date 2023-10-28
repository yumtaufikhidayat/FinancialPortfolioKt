package com.taufik.financialportofolio.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DataX(
    val nominal: Int = 0, // 1000000
    val trxDate: String = "" // 21/01/2023
): Parcelable