package com.taufik.financialportofolio.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taufik.financialportofolio.core.di.Injection
import com.taufik.financialportofolio.core.domain.usecase.TransactionsUseCase
import com.taufik.financialportofolio.ui.home.HomeViewModel

class ViewModelFactory private constructor(
    private val transactionsUseCase: TransactionsUseCase
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideTransactionUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(transactionsUseCase) as T
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}