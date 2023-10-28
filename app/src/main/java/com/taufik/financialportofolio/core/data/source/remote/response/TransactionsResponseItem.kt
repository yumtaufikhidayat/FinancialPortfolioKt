package com.taufik.financialportofolio.core.data.source.remote.response


data class TransactionsResponseItem(
    val data: List<Data> = listOf(),
    val type: String = "" // donutChart
)