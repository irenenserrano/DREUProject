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

class TesterGraphs: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // CODE FOR LINE GRAPH
        // create a new data series
        val series1 = LineGraphSeries<DataPoint>()
        val series2 = LineGraphSeries<DataPoint>()
        // link activity to layout view
        val graph: GraphView = findViewById(R.id.line_chart)

        // try to read and save data into series
        val input = InputStreamReader(assets.open("tester_data_CSV.csv"))
        val reader = BufferedReader(input)
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

        // add titles to series
        series1.title = "Series 1"
        series2.title = "Series 2"

        // add series to graph
        graph.addSeries(series1)
        graph.addSeries(series2)

        // display legend
        graph.legendRenderer.isVisible = true
        graph.legendRenderer.setFixedPosition(10,10)

        //set max x range
        graph.viewport.setMaxX(25.0)
        graph.viewport.isXAxisBoundsManual = true

        // CODE FOR SCATTER GRAPH
        //create new data series
        val xySeries1 = PointsGraphSeries<DataPoint>()
        val xySeries2 = PointsGraphSeries<DataPoint>()

        //create a graph view
        val scatterGraph: GraphView = findViewById(R.id.scatter_plot)

        // add data to series
        val input2 = InputStreamReader(assets.open("tester_data_scatter.csv"))
        val reader2 = BufferedReader(input2)
        var line2: String?
        var xScatter: Double
        var y1Scatter: Double
        var y2Scatter: Double
        while(reader2.readLine().also { line2 = it } != null) {
            val row2: List<String> = line2!!.split(",")
            Log.i("Row", row2.toString())
            xScatter = row2[0].toDouble()
            y1Scatter = row2[1].toDouble()
            y2Scatter = row2[2].toDouble()
            xySeries1.appendData(DataPoint(xScatter, y1Scatter), true, 100)
            xySeries2.appendData(DataPoint(xScatter, y2Scatter), true, 100)
        }

        // add style to the shape series
        xySeries1.shape = PointsGraphSeries.Shape.RECTANGLE
        xySeries1.color = Color.CYAN
        xySeries1.size = 20f

        xySeries2.shape = PointsGraphSeries.Shape.TRIANGLE
        xySeries2.color = Color.RED
        xySeries2.size = 20f

        // add titles to series
        xySeries1.title = "Series 1"
        xySeries2.title = "Series 2"

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

        // display legend
        scatterGraph.legendRenderer.isVisible = true
        scatterGraph.legendRenderer.setFixedPosition(10,10)
    }
}