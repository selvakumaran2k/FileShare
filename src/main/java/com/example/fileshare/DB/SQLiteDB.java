package com.example.fileshare.DB;//package com.app.DB;
//
//import java.sql.*;
//
//// database connection class
//// singleton type
//
//public class SQLiteDB implements DBInterface{
//    private static SQLiteDB instance = null;
//    Connection c = null;//connection to DB
//
//    public static SQLiteDB getInstance() {
//        if (instance == null) {
//            instance = new SQLiteDB();
//            instance.startConnection();
//        }
//        return instance;
//    }
//
//    //start connection
//    public boolean startConnection() {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:test.db");
//            c.setAutoCommit(false);
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            return false;
//        }
//        System.out.println("SQLite Opened database successfully");
//        return true;
//    }
//
//    // execute sql statements
//    public boolean execute(String sql) {
//        System.out.println(sql);
//        Statement stmt = null;
//        try {
//            stmt = c.createStatement();
//            stmt.executeUpdate(sql);
//            stmt.close();
//            c.commit();
//        } catch (Exception e) {
//            e.getStackTrace();
//            return false;
//        }
//        System.out.println("Records created successfully");
//        return true;
//    }
//
//    //getDateFrom DB
//    public ResultSet getData(String sql) {
//        System.out.println(sql);
//        Statement stmt = null;
//        ResultSet rs = null;
//        try {
//            stmt = c.createStatement();
//            rs = stmt.executeQuery(sql);
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            e.getStackTrace();
//
//        }
//        System.out.println("Operation done successfully");
//        return rs;
//    }
//
//    //close connection
//    public boolean close() {
//        try {
//            c.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//        System.out.println("db closed successfully");
//        return true;
//    }
//
//}
