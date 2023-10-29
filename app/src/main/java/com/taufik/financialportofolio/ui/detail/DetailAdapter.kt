package com.taufik.financialportofolio.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taufik.financialportofolio.R
import com.taufik.financialportofolio.core.data.source.remote.response.DataX
import com.taufik.financialportofolio.core.utils.CommonDateFormat.D_MMM_YYYY_FORMAT
import com.taufik.financialportofolio.core.utils.CommonDateFormat.YYYY_MM_DD_FORMAT
import com.taufik.financialportofolio.core.utils.convertDate
import com.taufik.financialportofolio.core.utils.convertToCurrency
import com.taufik.financialportofolio.databinding.ItemTransactionsBinding

class DetailAdapter: ListAdapter<DataX, DetailAdapter.ViewHolder>(DETAIL_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTransactionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemTransactionsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data : DataX) {
            binding.apply {
                tvTransactionDate.text = itemView.context.getString(
                    R.string.txt_transaction_date,
                    data.trxDate.convertDate(
                        YYYY_MM_DD_FORMAT,
                        D_MMM_YYYY_FORMAT
                    )
                )

                tvTransactionAmount.text = itemView.context.getString(
                    R.string.txt_transaction_amount,
                    convertToCurrency(data.nominal.toLong())
                )
            }
        }
    }

    companion object {
        private val DETAIL_DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataX>() {
            override fun areItemsTheSame(oldItem: DataX, newItem: DataX): Boolean = oldItem.trxDate == newItem.trxDate
            override fun areContentsTheSame(oldItem: DataX, newItem: DataX): Boolean = oldItem == newItem
        }
    }
}