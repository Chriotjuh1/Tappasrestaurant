package com.example.tapasrestaurant

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tapasrestaurant.entity.Gerecht
import com.example.tapasrestaurant.model.DbConnect

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Opstarten van view
        var lv = findViewById<ListView>(R.id.orderView)
        var lv2 = findViewById<ListView>(R.id.orderView)
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

                // We need to get the name of gerechten out of gerechten
                // Into the gerecht
                gerechten.forEach { gerecht ->
                    gerechtenA.add(gerecht.naam + " €" + gerecht.prijs)
                }
                arrayAdapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1, gerechtenA
                )
                lv.adapter = arrayAdapter
                lv2.adapter = arrayAdapter
            }

        }


        lv.setOnItemClickListener { parent, view, position, id ->


            val gerecht = gerechtenView[position] // The item that was clicked
            // val intent = Intent(this, BookDetailActivity::class.java)
            // startActivity(intent)
            val toast = Toast.makeText(applicationContext, "Geselecteerd: " + gerecht.naam, Toast.LENGTH_SHORT)
            toast.show()
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
        btnBestellen.setOnClickListener {
            // TODO bestellen naar database...
            var db2 = DbConnect();


            //val mButton = findViewById<Button>(R.id.btnBestellen)

            // When Button is clicked,
            //mButton.setOnClickListener {

            // val items = arrayOf("Red", "Orange", "Yellow", "Blue")

            var gerechtenA = arrayListOf<String>()
            // We need to get the name of gerechten out of gerechten
            // Into the gerecht
            gerechtenGeselecteerd.forEach { gerecht ->
                gerechtenA.add(gerecht.naam + " €" + gerecht.prijs)
            }
            val gerechtenGeselecteerd_Array: Array<String> = gerechtenA.toTypedArray()
            val builder = AlertDialog.Builder(this)


            with(builder)
            {
                setTitle("Bestelling")
                setNegativeButton("TERUG", null)


                setItems(gerechtenGeselecteerd_Array) { dialog, which ->

                    Toast.makeText(
                        applicationContext,
                        gerechtenGeselecteerd_Array[which] + " is clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }




// setPositiveButton("BESTELLEN", DialogInterface.OnClickListener {
                // TODO save to DB insert

                // dialog, id ->


                val listView: ListView = findViewById(R.id.orderView)
                // val builder: AlertDialog.Builder =  (this) // Pas de context toe op de AlertDialog.Builder
                builder.setPositiveButton("BESTELLEN",



                    DialogInterface.OnClickListener { dialog, id ->
                        // Deselecteer alle geselecteerde items in de lijst
                        // wanneer de knop "Bestellen" in de AlertDialog wordt ingedrukt
                        db2.doDbConnect() { ->
                            db2.insertBestelling(gerechtenGeselecteerd)

                        }
                        gerechtenGeselecteerd.clear()
                        gerechtenA.clear()
                        listView.clearChoices() // Gebruik listView in plaats van lv
                        listView.invalidateViews() // Gebruik listView in plaats van lv

                    })
                val dialog: AlertDialog = builder.create()
                dialog.show()




                    builder.setNeutralButton("Verwijderen") { dialog, id ->
                        gerechtenGeselecteerd.clear()
                        gerechtenA.clear()

                    }


                    StartActivity()


                    setNegativeButton("TERUG", null)
                    dialog.cancel()

                    show()


                }





                    }


                    //Creating dialog box
                    //Creating dialog box
                    //val alert = builder.create()
                    //Setting the title manually
                    //Setting the title manually
                    //alert.setTitle("AlertDialogExample")
                    //alert.show()
                }
                // Alert DialogBuilder is initialized
                // val mAlertDialogBuilder = AlertDialog.Builder(this)

                // Row layout is inflated and added to ListView
//                val mRowList = layoutInflater.inflate(R.layout.activity_listview, null)
//                val mListView = mRowList.findViewById<ListView>(R.id.ListView2)
//
//                // Adapter is created and applied to ListView
//                val mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, gerechtenGeselecteerd)
//                mListView.adapter = mAdapter
//                mAdapter.notifyDataSetChanged()


                // Row item is set as view in the Builder and the
                // ListView is displayed in the Alert Dialog
//                mAlertDialogBuilder.setView(mListView)
//                val dialog = mAlertDialogBuilder.create()
//                dialog.show()
                //  }

            }



