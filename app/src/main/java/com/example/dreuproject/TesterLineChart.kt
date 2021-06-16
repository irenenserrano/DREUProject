package com.example.dreuproject

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import org.achartengine.GraphicalView
import org.achartengine.chart.PointStyle
import org.achartengine.model.XYSeries
import org.achartengine.renderer.XYMultipleSeriesRenderer
import org.achartengine.renderer.XYSeriesRenderer

class TesterLineChart: Activity(){
    var series = XYSeries("Testing")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create data
        var counter: Double = 0.0
        for (i in 0..15) {
            // add data to series
            series.add(counter++, 2*i+3.0)
        }

        // create renderer
        val renderer = XYSeriesRenderer()

        // use renderer to create a line for the chart
        renderer.lineWidth = 2F
        // use renderer to set the line color
        renderer.color = Color.RED
        // use renderer to create data points
        renderer.pointStyle = PointStyle.CIRCLE
        // use rendered to set size of data point
        renderer.pointStrokeWidth = 3F

        // create master renderer and add the for now single rendered to it
        val mRenderer = XYMultipleSeriesRenderer()
        mRenderer.addSeriesRenderer(renderer)

        // make aesthetic changes to the main renderer
        mRenderer.marginsColor = Color.argb(0x00, 0xff, 0x00, 0x00) // this is a transparent border
        mRenderer.setPanEnabled(false, false) // disable panning on two axis
        mRenderer.setYAxisMax(35.0)
        mRenderer.setYAxisMin(0.0)
        mRenderer.setShowGrid(true)

        // create the view
        val chartView: GraphicalView = ChartFactory.getLineChartView(this, dataset, mRenderer)
    }


}
