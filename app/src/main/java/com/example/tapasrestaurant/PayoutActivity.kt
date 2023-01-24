package com.example.tapasrestaurant

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tapasrestaurant.entity.Gerecht
import com.example.tapasrestaurant.entity.Receipt
import com.example.tapasrestaurant.entity.ReceiptAdapter
import com.example.tapasrestaurant.entity.SelectedItems
import com.example.tapasrestaurant.model.DbConnect
import java.text.DecimalFormat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PayoutActivity : Activity() {

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payout)
        val gerechtenGeselecteerdArray = SelectedItems.instance.gerechtenGeselecteerdArray
        val adapter = ReceiptAdapter(gerechtenGeselecteerdArray)
        val gerechtenQuantity = HashMap<Gerecht, Int>()
        val text = "You don't have anything to payout!"
        val duration = Toast.LENGTH_SHORT
        val toasty = Toast.makeText(applicationContext, text, duration)

        try {
            val gerechtenReceipt = intent.getSerializableExtra("gerechtenReceipt") as ArrayList<Gerecht>
            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            gerechtenReceipt.forEach { gerecht ->
                gerechtenGeselecteerdArray.add(gerecht)
            }

            val adapter = ReceiptAdapter(gerechtenGeselecteerdArray)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
            adapter.notifyDataSetChanged()
            val formatter = DecimalFormat("#.00")
            val totaalPrijs = gerechtenGeselecteerdArray.sumOf { it.prijs.toDouble() }
            val formattedTotalPrice = formatter.format(totaalPrijs)
            val textViewTotaalprijs = findViewById<TextView>(R.id.text_view_totaalprijs)
            textViewTotaalprijs.text = "Total Amount: €$formattedTotalPrice"
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } catch (e: Exception) {
            Log.e("Error", "Er is een fout opgetreden: ${e.message}")
        }

        val btnOpenActivity: Button = findViewById(R.id.btnTerug)
        val btnContant: Button = findViewById(R.id.btnContant)
        val btnPin: Button = findViewById(R.id.btnPin)
        val db = DbConnect()


        btnContant.setOnClickListener {
            if (gerechtenGeselecteerdArray.isNotEmpty()) {
                val intent = Intent(this, WaitForPaymentVerifyActivity::class.java)
                db.doDbConnect() { ->
                    db.insertPayoutWaitlist("C")
                }
                startActivity(intent)
                finish()
            } else {
                toasty.show()
            }
        }

        btnPin.setOnClickListener {
            if (gerechtenGeselecteerdArray.isNotEmpty()) {
                val intent = Intent(this, WaitForPaymentVerifyActivity::class.java)
                db.doDbConnect() { ->
                    db.insertPayoutWaitlist("P")
                }
                startActivity(intent)
                finish()
            } else {
                toasty.show()
            }
        }

        btnOpenActivity.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        }
}



