package com.example.tapasrestaurant.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.tapasrestaurant.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondactivity)

        val txtHello : TextView = findViewById(R.id.btnVolgende)
        txtHello.setText("Welkom, dit is de nieuwe pagina")
        }
    }
