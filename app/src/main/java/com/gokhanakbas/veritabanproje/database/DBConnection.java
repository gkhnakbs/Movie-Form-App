package com.gokhanakbas.veritabanproje.database;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.gokhanakbas.veritabanproje.LoginPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {


    // guncel
    public static Connection connection;
    private final String host = "10.0.2.2";

    private final String database = "MovieFormApp";
    private final int port = 5432;
    private final String user = "postgres";
    private final String pass = "admin";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;
    private String SQL;

    public Connection DatabaseConnection()
    {
        this.url = String.format(this.url, this.host, this.port, this.database);
        connect();
        //this.disconnect();
        System.out.println("connection status:" + status);
        System.out.println("CONNECTİON:"+connection);
        return connection;
    }

    private void connect()
    {
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

                }
                catch (Exception e)
                {
                    status = false;
                    System.out.print(e.getMessage());
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


    public Connection getExtraConnection()
    {
        Connection c = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }
}