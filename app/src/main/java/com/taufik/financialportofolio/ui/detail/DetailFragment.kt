package com.taufik.financialportofolio.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.financialportofolio.R
import com.taufik.financialportofolio.core.data.source.remote.response.Data
import com.taufik.financialportofolio.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val detailAdapter by lazy { DetailAdapter() }
    private var data: Data? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initAdapter()
        getBundleData()
        setTransactionsData()
    }

    private fun initToolbar() {
        binding.toolbarDetail.apply {
            tvTitle.text = getString(R.string.txt_detail)
            imgBack.visibility = View.VISIBLE
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initAdapter() {
        binding.rvTransactions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = detailAdapter
        }
    }

    private fun getBundleData() {
        data = arguments?.getParcelable(EXTRA_LIST_TRANSACTION)
    }

    private fun setTransactionsData() {
        data?.let { data ->
            binding.tvLabelAndPercentage.text = getString(
                R.string.txt_label_percentage,
                data.label,
                data.percentage,
                "%"
            )
            detailAdapter.submitList(data.dataX)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_LIST_TRANSACTION = "com.taufik.financialportofolio.ui.detail.EXTRA_LIST_TRANSACTION"
    }
}