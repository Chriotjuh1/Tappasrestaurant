package com.example.tapasrestaurant

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tapasrestaurant.controller.PayoutActivity
import com.example.tapasrestaurant.controller.StartActivity
import com.example.tapasrestaurant.entity.Gerecht
import com.example.tapasrestaurant.model.DbConnect

class MenuActivity : AppCompatActivity() {

    var adapter: MyAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Opstarten van view
        var lv = findViewById<ListView>(R.id.orderView)
        var lv2 = findViewById<ListView>(R.id.orderView)

        var arrayAdapter: ArrayAdapter<*>

        // Opstarten van model -> (dbconnectie in model-file?)
        var db = DbConnect();
        var gerechtenView = ArrayList<Gerecht>()
        var gerechtenGeselecteerd = ArrayList<Gerecht>()
        var gerechtenReceipt = ArrayList<Gerecht>()
        // TODO Arraylist met selected
        // Database connection
        db.doDbConnect() { ->

            // Database query
            db.getGerechten { gerechten ->
                gerechtenView = gerechten
                // use arrayadapter and define an array

                val gerechtenA = arrayListOf<String>()
                gerechten.forEach { gerecht ->
                    val priceRightAligned = String.format("%-60s %s", gerecht.naam, "€" + gerecht.prijs)
                    gerechtenA.add(priceRightAligned)
                }

                adapter = MyAdapter(this, gerechten)
                lv.adapter = adapter

                }

        }

        val imageView = findViewById<ImageView>(androidx.appcompat.R.id.list_item)
        lv.setOnItemClickListener { parent, view, position, id ->
            val gerecht = gerechtenView[position]
            val toast = Toast.makeText(applicationContext, "Selected: " + gerecht.naam, Toast.LENGTH_SHORT)
            toast.show()
            gerechtenGeselecteerd.add(gerecht)
        }

        val btnBetalen: Button = findViewById(R.id.btnBetalen)
        btnBetalen.setOnClickListener {
            val intent = Intent(this, PayoutActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("gerechtenReceipt", gerechtenReceipt)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        }

        val btnBestellen: Button = findViewById(R.id.btnBestellen)
        btnBestellen.setOnClickListener {
            // TODO bestellen naar database...

            var db2 = DbConnect()
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
                setTitle("Order List")
                setNegativeButton("TERUG", null)

                setItems(gerechtenGeselecteerd_Array) { dialog, which ->

                    Toast.makeText(
                        applicationContext,
                        gerechtenGeselecteerd_Array[which] + " is clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // TODO save to DB insert

                val listView: ListView = findViewById(R.id.orderView)

                val gerechtenString = StringBuilder()
                val gerechtenCount = gerechtenGeselecteerd.groupingBy { it }.eachCount()
                gerechtenCount.forEach { (gerecht, count) ->
                    gerechtenString.append("$count x ${gerecht.naam + " €" + gerecht.prijs}\n")
                    builder.setMessage(gerechtenString.toString())
                }
                    // val builder: AlertDialog.Builder =  (this) // Pas de context toe op de AlertDialog.Builder
                    builder.setPositiveButton("ORDER",


                        DialogInterface.OnClickListener { dialog, id ->
                            // Deselecteer alle geselecteerde items in de lijst
                            // wanneer de knop "Bestellen" in de AlertDialog wordt ingedrukt
                            if (gerechtenGeselecteerd != null) {
                                gerechtenReceipt.addAll(gerechtenGeselecteerd)
                            }
                            db2.doDbConnect() { ->
                                db2.insertBestelling(gerechtenGeselecteerd)
                            }
                            gerechtenGeselecteerd.clear()
                            gerechtenA.clear()
                            listView.clearChoices() // Gebruik listView in plaats van lv
                            listView.invalidateViews() // Gebruik listView in plaats van lv

                            if (gerechtenGeselecteerd_Array.isEmpty()) {
                                val toast = Toast.makeText(
                                    applicationContext,
                                    "Please select a dish",
                                    Toast.LENGTH_SHORT
                                )
                                toast.show()
                            } else {
                                val toast =
                                    Toast.makeText(applicationContext, "The order has been sent!", Toast.LENGTH_SHORT)
                                toast.show()
                            }

                        })

                    val dialog: AlertDialog = builder.create()


                    builder.setNeutralButton("DELETE") { dialog, id ->
                        if (gerechtenGeselecteerd.isEmpty()) {
                            Toast.makeText(
                                applicationContext,
                                "Please select a dish",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "The dishes were succesfully deleted",
                                Toast.LENGTH_SHORT
                            ).show()
                            Toast.makeText(applicationContext, "Please selected your dishes again", Toast.LENGTH_SHORT)
                                .show()
                            gerechtenGeselecteerd.clear()
                            gerechtenA.clear()
                        }
                    }
                    StartActivity()
                    setNegativeButton("BACK", null)
                    dialog.cancel()
                    show()
                }
            }
    }


}

//Class MyAdapter
class MyAdapter(private val context: Context, private val arrayList: java.util.ArrayList<Gerecht>) : BaseAdapter() {
    private lateinit var naam: TextView
    private lateinit var prijs: TextView
    private lateinit var image: ImageView

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        naam = convertView.findViewById(R.id.naam)
        prijs = convertView.findViewById(R.id.prijs)
        image = convertView.findViewById(R.id.image_dish)

        naam.text = arrayList[position].naam
        prijs.text ="€" + arrayList[position].prijs

        if (position == 0) {
            image.setImageResource(R.drawable.spareribssss)
        } else if (position == 1) {
            image.setImageResource(R.drawable.hotdoggg)
        } else if (position == 2) {
            image.setImageResource(R.drawable.spaghettiii)
        } else if (position == 3) {
            image.setImageResource(R.drawable.aspergesss)
        } else if (position == 4) {
            image.setImageResource(R.drawable.chickenn)
        } else if (position == 5) {
            image.setImageResource(R.drawable.gehaktt)
        }

            return convertView

        }
    }










