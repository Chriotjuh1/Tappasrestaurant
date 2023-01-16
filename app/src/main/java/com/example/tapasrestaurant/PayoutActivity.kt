package com.example.tapasrestaurant

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tapasrestaurant.entity.Receipt
import com.example.tapasrestaurant.entity.ReceiptAdapter
import com.example.tapasrestaurant.model.DbConnect

class PayoutActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payout)

        val gerechtenGeselecteerd = ArrayList<Receipt>()
        gerechtenGeselecteerd.add(Receipt("Item 1", "€5.00"))
        gerechtenGeselecteerd.add(Receipt("Item 2", "€10.00"))
        gerechtenGeselecteerd.add(Receipt("Item 3", "€15.00"))
        gerechtenGeselecteerd.add(Receipt("Item 4", "€20.00"))

        // Create an adapter and set it to the RecyclerView
        val adapter = ReceiptAdapter(gerechtenGeselecteerd)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter


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
