package com.taufik.financialportofolio.core.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.taufik.financialportofolio.core.data.source.remote.network.ApiResponse
import com.taufik.financialportofolio.core.data.source.remote.response.TransactionsResponseItem
import com.taufik.financialportofolio.core.utils.JsonHelper
import org.json.JSONException

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllTransactions(): LiveData<ApiResponse<List<TransactionsResponseItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<TransactionsResponseItem>>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            try {
                val dataArray = jsonHelper.loadData()
                if (dataArray.isNotEmpty()) {
                    resultData.value = ApiResponse.Success(dataArray)
                } else {
                    resultData.value = ApiResponse.Empty
                }
            } catch (e: JSONException) {
                resultData.value = ApiResponse.Error(e.toString())
            }
        }, 2000)

        return resultData
    }
}