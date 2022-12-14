package com.example.tapasrestaurant.model;

import android.content.Context;
import com.example.tapasrestaurant.entity.Gerecht;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import android.provider.Settings;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLException;
public class DbConnect  {
    public Connection connection;

    // For Amazon Postgresql
    // private final String host = "ssprojectinstance.csv2nbvvgbcb.us-east-2.rds.amazonaws.com"

    // For Google Cloud Postgresql
    // private final String host = "35.44.16.169";

    // For Local PostgreSQL
    private final String host = "100.94.99.41";
    private final String database = "tappas";
    private final int port = 5432;
    private final String user = "softwareguys";
    private final String pass = "T^jTc4rv*!M6FKYVybFbuQG&v^HVkJ$KfRd1PCuAc$$@rEVvrJvKH%9#7gFQ";
    private String url = "jdbc:postgresql://%s:%d/%s";

    private boolean status;
    public String statusS;
    Statement stmt = null;

    ArrayList<Gerecht> gerechten = new ArrayList<Gerecht>();

    public interface ConnectionQueryCallback {
        void callbackCall(ArrayList<Gerecht> gerechten);
    }

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
                    //System.out.println("connected:" + status);
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
    public void getGerechten(final ConnectionQueryCallback callback)
    {
        Thread thread = new Thread(new Runnable() {
            @Override public void run()
            {
                try {
                    stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery( "select * from public.gerecht;" );
                    while ( rs.next() ) {
                        // @TODO map data and show first on the console to see if it works
                        int id = rs.getInt("productid");
                        String  name = rs.getString("naam");
                        String price = rs.getString("prijs");
                        // Dit is een gerechten object/element die ik vul met de data van de database
                        Gerecht g = new Gerecht();
                        g.setNaam(name);
                        g.setProductid(id);
                        g.setPrijs(price);
                        // Adding element gerecht to the gerechten ArrayList
                        gerechten.add(g);
                    }
                    // Callback function to return (we could add gerechten to this callback function)
                    callback.callbackCall(gerechten);
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
    public void insertBestelling(ArrayList gerechten)
    {
        Thread thread = new Thread(new Runnable() {
            @Override public void run()
            {
                try {
                    stmt = connection.createStatement();
                    String sql = "";
                    for (int i = 0; i < gerechten.size(); i++) {
                        Gerecht g = (Gerecht) gerechten.get(i);
                        sql += "INSERT INTO public.bestelling (product_id, tafel_id, \"Prijs\") VALUES (" + g.getProduct_Id() + ", 1, " + g.getPrijs() + ");";
                    }
                    stmt.executeUpdate( sql );
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

    public void insertPayoutWaitlist(String betaalmethode)
    {
        Thread thread = new Thread(new Runnable() {
            @Override public void run()
            {
                try {
                    stmt = connection.createStatement();
                    String sql = "INSERT INTO public.betaling(\n" +
                            "\t\"Betaalmethode\", \"Tafel_ID\", \"Status\")\n" +
                            "\tVALUES ('"+ betaalmethode + "', 1, false);";
                    stmt.executeUpdate( sql );
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


//    public class TableManager {
//        private String getDeviceId() {
//            Context context = null;
//
//            return Settings.Secure.getString(context.getContentResolver(),
//                    Settings.Secure.ANDROID_ID);
//        }

//        public void useDeviceIdAsTableId(Connection connection) throws SQLException {
//            String tafel_id = getDeviceId();
//
//            try {
//                // Voer een SQL-query uit om een nieuwe tafel toe te voegen aan de database
//                // met het device ID als tafel ID
//                Statement statement = connection.createStatement();
//                String sql = "INSERT INTO bestelling (id, name) VALUES (" + tafel_id + ", 'Tafel " + tafel_id + "')";
//                statement.executeUpdate(sql);
//            } catch (SQLException e) {
//                // Als de tafel al bestaat in de database, voer dan een update uit om de naam van de tafel
//                // te wijzigen naar 'Table $deviceId'
//                Statement statement = connection.createStatement();
//                String sql = "UPDATE bestelling SET name = 'Tafel " + tafel_id + "' WHERE id = " + tafel_id;
//                statement.executeUpdate(sql);
//            }
//        }
    }




