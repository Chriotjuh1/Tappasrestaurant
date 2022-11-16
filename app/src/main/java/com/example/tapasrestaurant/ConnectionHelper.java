package com.example.tapasrestaurant;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection connection;
    String ip,port,db,un,pass;

    @SuppressLint("NewApi")
    public Connection conclass(){
        ip="10.10.10.40";
        port="5432";
        db="tappas";
        un="softwareguys";
        pass="T^jTc4rv*!M6FKYVybFbuQG&v^HVkJ$KfRd1PCuAc$$@rEVvrJvKH%9#7gFQ";
        StrictMode.ThreadPolicy tpolicy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tpolicy);
        Connection con =null;
        String ConnectionURL=null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databaseName="+ db+ ";user="+un+";password="+pass+";";
            con= DriverManager.getConnection(ConnectionURL);
        }
        catch (Exception ex)
        {
            Log.e("Error : ", ex.getMessage());
        }
        return con;
    }
}
