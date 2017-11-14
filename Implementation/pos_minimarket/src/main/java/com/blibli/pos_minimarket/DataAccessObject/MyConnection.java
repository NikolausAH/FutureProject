package com.blibli.pos_minimarket.DataAccessObject;

import java.sql.*;

public class MyConnection {
    public MyConnection(){}

    String url="jdbc:postgresql://localhost:5432/db_pos";
    private static final String user="postgres";
    private static final String pass="admin";
    public static Connection c;

    public void makeConnection(){
        System.out.println("Opening Database...");
        try
        {
            c = DriverManager.getConnection(url, user,pass);
            System.out.println(" Success");
        }catch(Exception EX){
            System.out.print(" Error opening the database... myConnection makeConnection");
            System.out.println(EX);
        }
    }
    public void closeConnection(){
        System.out.println("Closing database...");
        try{
            c.close();
            System.out.println(" Success");
        }catch(Exception EX){
            System.out.println("Error Closing the database... myConnection closeConnection");
            System.out.println(EX);
        }
    }
}
