package com.taufik.financialportofolio.core.data.source

import androidx.lifecycle.LiveData
import com.taufik.financialportofolio.core.data.source.remote.RemoteDataSource
import com.taufik.financialportofolio.core.data.source.remote.network.ApiResponse
import com.taufik.financialportofolio.core.data.source.remote.response.TransactionsResponseItem
import com.taufik.financialportofolio.core.domain.repository.ITransactionsRepository

class TransactionsRepository private constructor(
    private val remoteDataSource: RemoteDataSource
): ITransactionsRepository {

    override fun getAllTransactions(): LiveData<ApiResponse<List<TransactionsResponseItem>>> {
        return remoteDataSource.getAllTransactions()
    }

    companion object {
        @Volatile
        private var instance: TransactionsRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource
        ): TransactionsRepository =
            instance ?: synchronized(this) {
                instance ?: TransactionsRepository(remoteDataSource)
            }
    }
}