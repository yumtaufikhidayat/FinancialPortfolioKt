package com.taufik.financialportofolio.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.taufik.financialportofolio.R
import com.taufik.financialportofolio.core.data.source.remote.network.ApiResponse
import com.taufik.financialportofolio.core.ui.ViewModelFactory
import com.taufik.financialportofolio.databinding.FragmentHomeBinding
import com.taufik.financialportofolio.ui.detail.DetailFragment


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var homeViewModel: HomeViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initViewModel()
        setChart()
    }

    private fun initToolbar() {
        binding.toolbarHome.tvTitle.text = getString(R.string.app_name)
    }

    private fun initViewModel() {
        val factory = ViewModelFactory.getInstance(requireContext())
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    private fun setChart() {
        homeViewModel?.transaction?.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Success -> {
                    val data = it.data
                    val entries = ArrayList<PieEntry>()

                    data.forEach { item ->
                        item.data.forEach { data ->
                            val label = data.label
                            val percentage = data.percentage
                            val pieEntry = PieEntry(percentage.toFloat(), label)
                            pieEntry.data = data
                            entries.add(pieEntry)
                        }
                    }

                    val dataSet = PieDataSet(entries.distinctBy { order -> order.label }, "")
                    dataSet.colors = listOf(
                        Color.CYAN,
                        Color.GREEN,
                        Color.RED,
                        Color.MAGENTA
                    )

                    val pieData = PieData(dataSet)
                    pieData.setValueTextSize(16f)
                    binding.pieChart.apply {
                        this.data = pieData
                        setUsePercentValues(true)
                        isDrawHoleEnabled = true
                        description.isEnabled = false
                        setEntryLabelColor(R.color.black)
                        animateY(1000, Easing.EaseInOutQuad)
                        setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                            override fun onValueSelected(e: Entry?, h: Highlight?) {
                                if (e is PieEntry) {
                                    val bundle = bundleOf(
                                        DetailFragment.EXTRA_LIST_TRANSACTION to e.data
                                    )
                                    findNavController().navigate(R.id.detailFragment, bundle)
                                }
                            }

                            override fun onNothingSelected() {}
                        })
                    }
                }
                is ApiResponse.Error -> {}
                is ApiResponse.Empty -> {}
                else -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}