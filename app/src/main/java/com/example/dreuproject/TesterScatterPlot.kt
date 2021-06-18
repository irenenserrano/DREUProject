package com.example.dreuproject

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.PointsGraphSeries
import java.io.BufferedReader
import java.io.InputStreamReader

class TesterScatterPlot : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.tester_line_chart)

        //create new data series
        var xySeries1 = PointsGraphSeries<DataPoint>()
        var xySeries2 = PointsGraphSeries<DataPoint>()

        //create a graph view
        var graph: GraphView = findViewById(R.id.scatter_plot)

        // add data to series
        val minput = InputStreamReader(assets.open("tester_data_scatter.csv"))
        val reader = BufferedReader(minput)
        var line: String?
        var x: Double
        var y1: Double
        var y2: Double
        while(reader.readLine().also { line = it } != null) {
            val row: List<String> = line!!.split(",")
            Log.i("Row", row.toString())
            x = row[0].toDouble()
            y1 = row[1].toDouble()
            y2 = row[2].toDouble()
            xySeries1.appendData(DataPoint(x, y1), true, 100)
            xySeries2.appendData(DataPoint(x, y2), true, 100)
        }

        // add style to the shape series
        xySeries1.shape = PointsGraphSeries.Shape.RECTANGLE
        xySeries1.color = Color.CYAN
        xySeries1.size = 20f

        xySeries2.shape = PointsGraphSeries.Shape.TRIANGLE
        xySeries2.color = Color.RED
        xySeries2.size = 20f

        // set scrollable and scalable
        graph.viewport.isScalable = true
        graph.viewport.setScalableY(true)
        graph.viewport.isScrollable = true
        graph.viewport.setScrollableY(true)

        // set manual x boundaries
        graph.viewport.setMinX(0.0)
        graph.viewport.setMaxX(25.0)
        graph.viewport.isXAxisBoundsManual = true

        // set manual y boundaries
        graph.viewport.setMinY(0.0)
        graph.viewport.setMaxY(100.0)
        graph.viewport.isYAxisBoundsManual = true

        // add the series to the graph
        graph.addSeries(xySeries1)
        graph.addSeries(xySeries2)
    }
}