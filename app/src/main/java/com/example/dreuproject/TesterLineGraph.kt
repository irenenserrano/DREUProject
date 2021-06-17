package com.example.dreuproject

import android.app.Activity
import android.os.Bundle
import android.os.DropBoxManager
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
        lateinit var series: LineGraphSeries<DataPoint>
        // link activity to layout view
        val graph: GraphView = findViewById(R.id.line_chart)

        // try to read and save data into series
        val minput = InputStreamReader(assets.open("tester_data_CSV.csv"))
        val reader = BufferedReader(minput)

        var line: String
        var x: Double
        var y: Double
        while (reader.readLine().also { line = it } != null) {
            val row: List<String> = line.split(",")
            x = row[0].toDouble()
            y = row[1].toDouble()
            series.appendData(DataPoint(x,y),true,  100)
        }
        graph.addSeries(series)
    }
}