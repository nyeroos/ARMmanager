package com.example.armmanager.ui.analytics

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.armmanager.databinding.AddRequestBinding
import com.example.armmanager.databinding.AnalyticsBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.ChartData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


class AnalyticsFragment : Fragment() {

    private lateinit var binding: AnalyticsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = AnalyticsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val value = 1
        val value2 = 2
        var entries = ArrayList<PieEntry>()
        val chart: PieChart = binding.chart
        chart.setDrawHoleEnabled(false)
        chart.setDrawEntryLabels(false)
        chart.description.isEnabled = false

        chart.description.text = "Заявки"
        chart.description.textColor = Color.BLACK
        chart.description.textSize = 16f
        chart.description.setTextAlign(Paint.Align.LEFT)

        val legend: Legend = chart.legend
        legend.textSize = 14f
        entries.add(PieEntry(value.toFloat(), "Выполненные в срок"))
        entries.add(PieEntry(value2.toFloat(), "Просроченные"))
      //  entries.add(PieEntry(3f, 4f))

        val colors = listOf(
            Color.parseColor("#E6E6FA"),  // светло-лавандовый
            Color.parseColor("#FFC0CB"), // розовый
            Color.parseColor("#87CEFA"), // светло-голубой
            Color.parseColor("#98FB98"), // светло-зеленый
            Color.parseColor("#FFFFE0"), // светло-желтый
            Color.parseColor("#D3D3D3"), // светло-серый
            Color.parseColor("#FFE4B5") // светло-оранжевый

        )
        val dataSet = PieDataSet(entries, "")
        dataSet.setColors(colors)
        dataSet.valueTextSize = 14f
        val data = PieData(dataSet)
        chart.data = data


        return root
    }
}