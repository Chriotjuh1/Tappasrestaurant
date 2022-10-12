package com.example.tapasrestaurant.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tapasrestaurant.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondactivity)

        val  btnVolgende = findViewById<Button>(R.id.btnVolgende)
        btnVolgende.setOnClickListener {
            val Intent = Intent(this,SecondActivity::class.java)
            startActivity(Intent)
        }
    }
}