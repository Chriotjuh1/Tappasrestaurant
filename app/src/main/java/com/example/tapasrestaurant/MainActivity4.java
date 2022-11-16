package com.example.tapasrestaurant;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity4 extends AppCompatActivity {

    Connection connect;
    String connectionResult="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindata);

        TextView txt = findViewById(R.id.btnLoadData);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity4.this, "Deze button werkt", Toast.LENGTH_SHORT).show();
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
