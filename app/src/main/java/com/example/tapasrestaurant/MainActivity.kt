package com.example.tapasrestaurant

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.tapasrestaurant.databinding.ActivityMainBinding
import com.example.tapasrestaurant.model.DbConnect

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lv = findViewById<ListView>(R.id.ListView)

        var db = DbConnect();

        db.doDbConnect() { ->
            db.queryTest { gerechten ->

                // use arrayadapter and define an array
                val arrayAdapter: ArrayAdapter<*>
                val users = arrayOf(
                    "Virat Kohli", "Rohit Sharma", "Steve Smith",
                    "Kane Williamson", "Ross Taylor"
                )
                arrayAdapter = ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, users)
                lv.adapter = arrayAdapter
            }
        }





        // tabLayout = findViewById(R.id.)
        var btnOpenActivity: Button = findViewById(R.id.btnVolgende)
        btnOpenActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}