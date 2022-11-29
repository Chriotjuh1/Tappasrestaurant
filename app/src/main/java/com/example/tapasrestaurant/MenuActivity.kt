package com.example.tapasrestaurant

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tapasrestaurant.model.DbConnect
import com.example.tapasrestaurant.entity.Gerecht

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Opstarten van view
        var lv = findViewById<ListView>(R.id.ListView)
        var arrayAdapter: ArrayAdapter<*>

        // Opstarten van model -> (dbconnectie in model-file?)
        var db = DbConnect();

        var gerechtenView = ArrayList<Gerecht>()
        var gerechtenGeselecteerd = ArrayList<Gerecht>()

        // TODO Arraylist met selected


        // Database connection
        db.doDbConnect() { ->

            // Database query
            db.getGerechten { gerechten ->
                gerechtenView = gerechten
                // use arrayadapter and define an array

                val gerechtenA = arrayListOf<String>()

                // We need to get the name of gerechten out of gerechten..
                // Into the gerecht
                gerechten.forEach { gerecht ->
                    gerechtenA.add(gerecht.naam)
                }
                arrayAdapter = ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, gerechtenA)
                lv.adapter = arrayAdapter
            }

        }


        lv.setOnItemClickListener { parent, view, position, id ->


            val gerecht = gerechtenView[position] // The item that was clicked
            // val intent = Intent(this, BookDetailActivity::class.java)
            // startActivity(intent)
            val toast = Toast.makeText(applicationContext, "Geselecteerd: " + gerecht.naam, Toast.LENGTH_SHORT)
            toast.show()
            // TODO Voeg het geselecteerde gerecht toe aan je selected arraylist
            gerechtenGeselecteerd.add(gerecht)

        }


        // tabLayout = findViewById(R.id.)
        val btnBetalen: Button = findViewById(R.id.btnBetalen)
        btnBetalen.setOnClickListener {
            val intent = Intent(this, PayoutActivity::class.java)
            startActivity(intent)
            finish()
        }
        val btnBestellen: Button = findViewById(R.id.btnBestellen)
        btnBestellen.setOnClickListener{
            // TODO bestellen naar database...

            var db2 = DbConnect();
            db2.doDbConnect() { ->

                db2.insertBestelling(gerechtenGeselecteerd)
            }
        }
    }
}

