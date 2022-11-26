package com.example.tapasrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.tapasrestaurant.model.Gerecht;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
        TextView txtOut = findViewById(R.id.textView);
        TextView txtOut2 = findViewById(R.id.textView2);
        TextView txtOut3 = findViewById(R.id.textView3);



//                Toast.makeText(MainActivity4.this, "Deze button werkt", Toast.LENGTH_SHORT).show();
//                TextView txv1 =(TextView) findViewById(R.id.textView);
            // Use Class DbConnect
            db = new DbConnect();
            // Call do DB connection to make a connection and use callback
            db.doDbConnect( () -> {
                // TODO also use callback when error occurs and show error
                String statusS = db.statusS;
                Log.d("Callback", statusS);
                // Do a query
                db.queryTest( (ArrayList<Gerecht> gerechten) -> {
                    // THIS IS NEEDED TO UPDATE UI
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            // TODO use the gerechten ArrayList returned in callback for the listView
                            // Stuff that updates the UI
                            txtOut.setText( db.nameE );
                            txtOut2.setText( db.nameR );
                            txtOut3.setText( db.nameR );
                        }
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
