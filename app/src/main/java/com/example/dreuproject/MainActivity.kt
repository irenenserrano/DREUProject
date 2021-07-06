package com.example.dreuproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val header_one: TextView = findViewById(R.id.header_one)
        header_one.setOnClickListener {
            Toast.makeText(it.context, "Item Clicked", Toast.LENGTH_LONG).show()
        }

        val header_two: TextView = findViewById(R.id.header_two)
        header_two.setOnClickListener {
            Toast.makeText(it.context, "Item Clicked", Toast.LENGTH_LONG).show()
        }

        val header_three: TextView = findViewById(R.id.header_three)
        header_three.setOnClickListener {
            Toast.makeText(it.context, "Item Clicked", Toast.LENGTH_LONG).show()
        }
    }
}