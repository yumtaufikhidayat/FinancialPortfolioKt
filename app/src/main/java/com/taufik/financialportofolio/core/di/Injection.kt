package com.taufik.financialportofolio.core.di

import android.content.Context
import com.taufik.financialportofolio.core.data.source.TransactionsRepository
import com.taufik.financialportofolio.core.data.source.remote.RemoteDataSource
import com.taufik.financialportofolio.core.domain.usecase.TransactionsInteractor
import com.taufik.financialportofolio.core.domain.usecase.TransactionsUseCase
import com.taufik.financialportofolio.core.utils.JsonHelper

object Injection {
    private fun provideRepository(context: Context): TransactionsRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return TransactionsRepository.getInstance(remoteDataSource)
    }

    fun provideTransactionUseCase(context: Context): TransactionsUseCase {
        val repository = provideRepository(context)
        return TransactionsInteractor(repository)
    }
}