package com.example.dreuproject

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import java.io.BufferedReader
import java.io.InputStreamReader

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

        //create new data series
        var xySeries1 = PointsGraphSeries<DataPoint>()
        var xySeries2 = PointsGraphSeries<DataPoint>()

        //create a graph view
        var scatterGraph: GraphView = findViewById(R.id.scatter_plot)

        // add data to series
        val minput2 = InputStreamReader(assets.open("tester_data_scatter.csv"))
        val reader2 = BufferedReader(minput2)
        var line2: String?
        var x_scatter: Double
        var y1_scatter: Double
        var y2_scatter: Double
        while(reader2.readLine().also { line2 = it } != null) {
            val row2: List<String> = line2!!.split(",")
            Log.i("Row", row2.toString())
            x_scatter = row2[0].toDouble()
            y1_scatter = row2[1].toDouble()
            y2_scatter = row2[2].toDouble()
            xySeries1.appendData(DataPoint(x_scatter, y1_scatter), true, 100)
            xySeries2.appendData(DataPoint(x_scatter, y2_scatter), true, 100)
        }

        // add style to the shape series
        xySeries1.shape = PointsGraphSeries.Shape.RECTANGLE
        xySeries1.color = Color.CYAN
        xySeries1.size = 20f

        xySeries2.shape = PointsGraphSeries.Shape.TRIANGLE
        xySeries2.color = Color.RED
        xySeries2.size = 20f

        // set scrollable and scalable
        scatterGraph.viewport.isScalable = true
        scatterGraph.viewport.setScalableY(true)
        scatterGraph.viewport.isScrollable = true
        scatterGraph.viewport.setScrollableY(true)

        // set manual x boundaries
        scatterGraph.viewport.setMinX(0.0)
        scatterGraph.viewport.setMaxX(25.0)
        scatterGraph.viewport.isXAxisBoundsManual = true

        // set manual y boundaries
        scatterGraph.viewport.setMinY(0.0)
        scatterGraph.viewport.setMaxY(100.0)
        scatterGraph.viewport.isYAxisBoundsManual = true

        // add the series to the graph
        scatterGraph.addSeries(xySeries1)
        scatterGraph.addSeries(xySeries2)
    }
}