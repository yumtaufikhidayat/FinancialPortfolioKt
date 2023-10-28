package com.taufik.financialportofolio.core.utils

import android.content.Context
import com.taufik.financialportofolio.R
import com.taufik.financialportofolio.core.data.source.remote.response.Data
import com.taufik.financialportofolio.core.data.source.remote.response.DataX
import com.taufik.financialportofolio.core.data.source.remote.response.TransactionsResponseItem
import org.json.JSONArray
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.transactions).bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun loadData(): List<TransactionsResponseItem> {
        val list = ArrayList<TransactionsResponseItem>()
        val jsonArray = JSONArray(parsingFileToString())
        try {
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val type = jsonObject.getString("type")
                val data = jsonObject.getJSONArray("data")

                for (j in 0 until data.length()) {
                    val item = data.getJSONObject(j)
                    val label = item.getString("label")
                    val percentage = item.getString("percentage")
                    val dataDetails = item.getJSONArray("data")

                    for (k in 0 until dataDetails.length()) {
                        val details = dataDetails.getJSONObject(k)
                        val trxDate = details.getString("trx_date")
                        val nominal = details.getInt("nominal")

                        val listDataX = listOf(
                            DataX(
                                nominal = nominal,
                                trxDate = trxDate
                            )
                        )

                        val listData = listOf(
                            Data(
                                dataX = listDataX,
                                label = label,
                                percentage = percentage
                            )
                        )

                        val transactionsResponseItem = TransactionsResponseItem(
                            data = listData,
                            type = type
                        )
                        list.add(transactionsResponseItem)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }
}