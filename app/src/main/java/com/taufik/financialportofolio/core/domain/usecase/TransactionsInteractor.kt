package com.taufik.financialportofolio.core.domain.usecase

import androidx.lifecycle.LiveData
import com.taufik.financialportofolio.core.data.source.remote.network.ApiResponse
import com.taufik.financialportofolio.core.data.source.remote.response.TransactionsResponseItem
import com.taufik.financialportofolio.core.domain.repository.ITransactionsRepository

class TransactionsInteractor(private val transRepository: ITransactionsRepository): TransactionsUseCase {
    override fun getAllTransactions(): LiveData<ApiResponse<List<TransactionsResponseItem>>> {
        return transRepository.getAllTransactions()
    }
}