package com.example.dreuproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cardView: CardView = findViewById(R.id.base_cardview)
        val hiddenView: LinearLayout = findViewById(R.id.hidden_view)
        val headerOne: TextView = findViewById(R.id.header_one)

        // Add a click listener to header so that view can expand and collapse
        // Original start value of hiddenView.visibility is gone
        headerOne.setOnClickListener {
            // If the CardView is already expanded, set its visibility to gone
            // If it is not expanded, set its visibility to visible
            Log.d("testing", "testing")
            if (hiddenView.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                hiddenView.visibility = View.GONE
            } else {
                TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                hiddenView.visibility = View.VISIBLE
            }
        }

    }

}