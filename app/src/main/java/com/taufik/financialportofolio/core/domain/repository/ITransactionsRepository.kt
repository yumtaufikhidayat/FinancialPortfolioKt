package com.taufik.financialportofolio.core.domain.repository

import androidx.lifecycle.LiveData
import com.taufik.financialportofolio.core.data.source.remote.network.ApiResponse
import com.taufik.financialportofolio.core.data.source.remote.response.TransactionsResponseItem

interface ITransactionsRepository {
    fun getAllTransactions(): LiveData<ApiResponse<List<TransactionsResponseItem>>>
}