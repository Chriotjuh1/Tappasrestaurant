package com.example.tapasrestaurant

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.tapasrestaurant.model.DbConnect

class PayoutActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payout)

        val btnOpenActivity: Button = findViewById(R.id.btnTerug)
        val btnContant: Button = findViewById(R.id.btnContant)
        val btnPin: Button = findViewById(R.id.btnPin)

        val db = DbConnect()

        btnContant.setOnClickListener {
            val intent = Intent(this, WaitForPaymentVerifyActivity::class.java)
            db.doDbConnect() { ->
                db.insertPayoutWaitlist("C")
            }
            startActivity(intent)
            finish()
        }

        btnPin.setOnClickListener {
            val intent = Intent(this, WaitForPaymentVerifyActivity::class.java)
            db.doDbConnect() { ->
                db.insertPayoutWaitlist("P")
            }
            startActivity(intent)
            finish()
        }

        btnOpenActivity.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}