package com.taufik.financialportofolio.ui.home

import androidx.lifecycle.ViewModel
import com.taufik.financialportofolio.core.domain.usecase.TransactionsUseCase

class HomeViewModel(
    transactionsUseCase: TransactionsUseCase
) : ViewModel() {
    val transaction = transactionsUseCase.getAllTransactions()
}