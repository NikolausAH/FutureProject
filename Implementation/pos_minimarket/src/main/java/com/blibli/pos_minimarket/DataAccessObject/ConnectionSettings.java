package com.blibli.pos_minimarket.DataAccessObject;

import java.sql.*;

public class ConnectionSettings {

    protected Connection connection;

    public ConnectionSettings(){}
    public ConnectionSettings(Connection connection){
        this.connection=connection;
    }

    public void makeConnection(){
        System.out.println("Opening Database...");
        try
        {//
            String url="jdbc:postgresql://localhost:5432/db_pos";
            String user="postgres";
            String pass="admin";
            connection = DriverManager.getConnection(url, user,pass);
            System.out.println(" Success");
        }catch(Exception EX){
            System.out.print(" Error opening the database... myConnection makeConnection");
            System.out.println(EX.toString());
        }
    }
    public void closeConnection(){
        System.out.println("Closing database...");
        try{
            connection.close();
            System.out.println(" Success");
        }catch(Exception EX){
            System.out.println("Error Closing the database... myConnection closeConnection");
            System.out.println(EX.toString());
        }
    }
}
