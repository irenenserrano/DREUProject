package com.example.dreuproject

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.DropBoxManager
import android.util.Log
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

class TesterLineGraph: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tester_line_chart)

        // create a new data series
        var series1 = LineGraphSeries<DataPoint>()
        var series2 = LineGraphSeries<DataPoint>()
        // link activity to layout view
        val graph: GraphView = findViewById(R.id.line_chart)

        // try to read and save data into series
        val minput = InputStreamReader(assets.open("tester_data_CSV.csv"))
        val reader = BufferedReader(minput)
        var line: String?
        var x: Double
        var y1: Double
        var y2: Double
        while (reader.readLine().also { line = it } != null) {
            val row: List<String> = line!!.split(",")
            Log.i("Row", row.toString())
            x = row[0].toDouble()
            y1 = row[1].toDouble()
            y2 = row[2].toDouble()
            // add data to series, unsure what the last element is for
            series1.appendData(DataPoint(x,y1),true,  100)
            series2.appendData(DataPoint(x,y2), true, 100)
        }

        // add style to the series
        series1.color = Color.RED
        series2.color = Color.BLACK

        // add series to graph
        graph.addSeries(series1)
        graph.addSeries(series2)

        //set max x range
        graph.viewport.setMaxX(25.0)
        graph.viewport.isXAxisBoundsManual = true
    }
}