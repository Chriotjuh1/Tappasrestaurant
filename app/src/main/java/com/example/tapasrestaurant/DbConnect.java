package com.example.tapasrestaurant;
import com.example.tapasrestaurant.model.Gerecht;

import javax.security.auth.callback.Callback;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executor;

public class DbConnect  {
    public Connection connection;

    // For Amazon Postgresql
    // private final String host = "ssprojectinstance.csv2nbvvgbcb.us-east-2.rds.amazonaws.com"

    // For Google Cloud Postgresql
    // private final String host = "35.44.16.169";

    // For Local PostgreSQL
    private final String host = "10.10.10.40";
    private final String database = "tappas";
    private final int port = 5432;
    private final String user = "softwareguys";
    private final String pass = "T^jTc4rv*!M6FKYVybFbuQG&v^HVkJ$KfRd1PCuAc$$@rEVvrJvKH%9#7gFQ";
    private String url = "jdbc:postgresql://%s:%d/%s";

    private boolean status;
    public String statusS;
    Statement stmt = null;

    String nameR;
    String nameE;

    Gerecht[] gArray;

    public interface ConnectionCallback {
        void callbackCall();
    }

    // DbConnect makes a database connection to postgress
    // StatusS can be used to show the status
    // When using the class a connection is established automatically as the constructor is called
    public void doDbConnect( final ConnectionCallback callback)
    {
        this.url = String.format(this.url, this.host, this.port, this.database);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    System.out.println("connected:" + status);
                    statusS = "Connected";
                    callback.callbackCall();
                }
                catch (Exception e)
                {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                    statusS = e.getMessage();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }
    }


    // @todo query test is called and uses connection, so first a DB connection is needed bvefore calling this function
    //       if the class is created a connection is established automatically
    public void queryTest(final ConnectionCallback callback)
    {
        Thread thread = new Thread(new Runnable() {
            @Override public void run()
            {
                try {
                    stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery( "select * from public.gerecht;" );
                    while ( rs.next() ) {
                        // @TODO map data and show first on the console to see if it works
                        int id = rs.getInt("Product_ID");
                        String  name = rs.getString("Naam");
                        // Dit is een gerechten object die ik vul met de data van de database
                        Gerecht g = new Gerecht();
                        g.setNaam(name);
                        g.setProduct_Id(id);
                        gArray = new Gerecht[g];
                        // Hier moeten we het object g nog aan een array toevoegen
                        nameE = String.valueOf(id);
                        nameR = name;
                        System.out.printf( "Product_ID = %s , Naam = %s ", id,name );
                        System.out.println();
                        callback.callbackCall();
                    }

                    rs.close();
                    stmt.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }
    }
}
