package com.example.dreuproject

import android.app.Activity
import android.graphics.Color
import android.icu.text.DateFormatSymbols
import android.os.Bundle
import com.androidplot.xy.*
import java.text.FieldPosition
import java.text.ParsePosition


class TesterBarChart : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tester)

        val plot: XYPlot = findViewById(R.id.tester_bar)

        val bf1 = BarFormatter(Color.RED, Color.WHITE)
        val bf2 = BarFormatter(Color.BLACK, Color.BLUE)

        val series1: XYSeries = SimpleXYSeries(
            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
            "Series 1", 3, 4, 5, 3, 2, 3, 5, 6, 2, 1, 3, 1
        )

        val series2: XYSeries = SimpleXYSeries(
            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
            "Series 2", 0, 1, 1, 0, 1, 0, 0, 0, 2, 1, 0, 1
        )

//        val renderer = plot.getRenderer(BarRenderer::class.java)
//
//        renderer.setBarOrientation(BarRenderer.BarOrientation.SIDE_BY_SIDE);

        plot.addSeries(series1, bf1)
        plot.addSeries(series2, bf2)
    }
}