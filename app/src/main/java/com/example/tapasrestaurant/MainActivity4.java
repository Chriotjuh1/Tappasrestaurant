package com.example.tapasrestaurant;

import android.media.browse.MediaBrowser;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import org.json.JSONException;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

public class MainActivity4 extends AppCompatActivity {

    Connection connect;
    String connectionResult="";
    DbConnect db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use layout
        setContentView(R.layout.activity_maindata);
        // Get the textview to show something in the textview
        TextView txt = findViewById(R.id.btnLoadData);

        txt.setOnClickListener(v -> {

//                Toast.makeText(MainActivity4.this, "Deze button werkt", Toast.LENGTH_SHORT).show();
//                TextView txv1 =(TextView) findViewById(R.id.textView);
            // Use Class DbConnect
            db = new DbConnect();
            // Call do DB connection to make a connection and use callback
            db.doDbConnect( () -> {
                // TODO also use callback when error occurs and show error
                String statusS = db.statusS;
                Log.d("Callback", statusS);

                // TODO Look into Second call with callback
                // TODO return some result add a first parameter ( and make an object class what we expect and fill that out of the query )
                db.queryTest( () -> {
                    // TODO use txt view to show something..
                    // BUT first look into logs and break
                });
            });

        });
    }

    public void GetDataToTextView(View v)
    {
        TextView txv1 =(TextView) findViewById(R.id.textView);
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect= connectionHelper.conclass();
            if(connect!=null)
            {
                String query = "select * from gerecht";
                Statement smt= connect.createStatement();
                ResultSet rs = smt.executeQuery(query);

                while(rs.next())
                {
                    txv1.setText(rs.getString(1));
                }
            }
            else
            {
                connectionResult="Check Connection";
            }

        }
        catch (Exception ex)
        {
            Log.e("Error :", ex.getMessage());
        }
    }
}
