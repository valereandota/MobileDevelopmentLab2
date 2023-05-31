package com.example.lab21.ui.analysis

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.lab21.databinding.FragmentAnalysisBinding
import com.example.lab21.ui.SharedViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class AnalysisFragment : Fragment() {

    private var _binding: FragmentAnalysisBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalysisBinding.inflate(inflater, container, false)

        val sharedPreferences = requireActivity().getSharedPreferences("results", Context.MODE_PRIVATE)
        val entriesStringSet = sharedPreferences.getStringSet("entries", mutableSetOf()) ?: mutableSetOf()

        val entries: List<PieEntry> = entriesStringSet.map { entryString ->
            val (x, y) = entryString.split(":").map { it.toFloat() }
            PieEntry(y, x)
        }

        if (entries.isNotEmpty()) {
            val pieChart: PieChart = binding.pieChart

            val pieDataSet = PieDataSet(entries, "")

            pieDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
            pieDataSet.valueTextColor = Color.BLACK

            val pieData = PieData(pieDataSet)

            pieChart.data = pieData
            pieChart.description.isEnabled = false

            pieChart.animateY(2000)
            pieChart.invalidate()
        }


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
