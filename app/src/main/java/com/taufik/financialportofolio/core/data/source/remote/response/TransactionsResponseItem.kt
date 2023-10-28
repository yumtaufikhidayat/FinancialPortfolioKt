package com.taufik.financialportofolio.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class TransactionsResponseItem(
    val data: List<Data> = listOf(),
    val type: String = "" // donutChart
): Parcelable