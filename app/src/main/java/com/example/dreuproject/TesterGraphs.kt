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
        setContentView(R.layout.tester_scroll_view)
        // CODE FOR LINE GRAPH
        // create a new data series
        val series = LineGraphSeries<DataPoint>()
        // link activity to layout view
        val graph: GraphView = findViewById(R.id.line_chart)

        // append data to series
        var x: Double = 0.0
        var y: Double
        for (i in 1..25) {
            x = x + 1.0
            y = 2*x
            series.appendData(DataPoint(x, y), true, 26)
        }


        // add series to graph
        graph.addSeries(series)

        //set max x range
        graph.viewport.setMaxX(25.0)
        graph.viewport.isXAxisBoundsManual = true
    }
}