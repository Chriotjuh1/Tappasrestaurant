package com.example.tapasrestaurant

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class PayoutActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payout)

        val btnOpenActivity: Button = findViewById(R.id.btnTerug)
        btnOpenActivity.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}