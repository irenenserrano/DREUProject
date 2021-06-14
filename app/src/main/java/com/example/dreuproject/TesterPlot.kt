package com.example.dreuproject

import android.app.Activity
import android.graphics.DashPathEffect
import android.os.Bundle
import com.androidplot.util.PixelUtils
import com.androidplot.xy.*
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*


class TesterPlot : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tester)

        // Initialize the XYplot
        val plot: XYPlot = findViewById(R.id.tester)

        //create arrays that will act as our y values
        var domainLabels: IntArray = intArrayOf(1, 2, 3, 6, 7, 8, 9, 10, 13, 14)
        val series1Numbers: IntArray = intArrayOf(1, 4, 2, 8, 4, 16, 8, 32, 16, 64)
        val series2Numbers: IntArray = intArrayOf(5, 2, 10, 5, 20, 10, 40, 20, 80, 40)

        // Turn the y values into XYseries
        val series1: XYSeries = SimpleXYSeries(
            series1Numbers.toMutableList(),
            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
            "Series1"
        )
        val series2: XYSeries = SimpleXYSeries(
            series2Numbers.toMutableList(),
            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
            "Series2"
        )

        // create formatters to use for drawing a series using a LineAndPointRenderer
        // and configure them from xml
        val series1Format: LineAndPointFormatter =
            LineAndPointFormatter(this, R.xml.line_point_formater_with_labels)
        var series2Format: LineAndPointFormatter =
            LineAndPointFormatter(this, R.xml.line_point_formater_with_labels_2)

        // add a dashed-line effect to series2 line:
        series2Format.getLinePaint().setPathEffect(
            DashPathEffect(
                floatArrayOf(
                    PixelUtils.dpToPix(20F),
                    PixelUtils.dpToPix(15F)
                ), 0F
            )
        )

        // add smooth-effect to the lines
        series1Format.setInterpolationParams(
            CatmullRomInterpolator.Params(
                10,
                CatmullRomInterpolator.Type.Centripetal
            )
        )
        series2Format.setInterpolationParams(
            CatmullRomInterpolator.Params(
                10,
                CatmullRomInterpolator.Type.Centripetal
            )
        )

        // add the series to the plot
        plot.addSeries(series1, series1Format)
        plot.addSeries(series2, series2Format)

        plot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format() {
            override fun format(
                obj: Any,
                toAppendTo: StringBuffer,
                pos: FieldPosition?
            ): StringBuffer? {
                val i = Math.round((obj as Number).toFloat())
                return toAppendTo.append(domainLabels[i])
            }

            override fun parseObject(source: String?, pos: ParsePosition?): Any? {
                return null
            }
        }

    }
}