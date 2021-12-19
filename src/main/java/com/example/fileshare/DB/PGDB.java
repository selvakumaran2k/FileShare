package com.example.fileshare.DB;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class PGDB implements DBInterface {
    private static PGDB instance = null;
    private Connection c = null;//connection to DB
    private static String drivers;
    private static String connectionURL;
    private static String username;
    private static String password;
    static
    {
        try{
            Properties prop=new Properties();
            String base = System.getProperty("user.dir");
            FileInputStream in = new FileInputStream(base+"\\Database.properties");
            prop.load(in);
            in.close();

            PGDB.drivers = prop.getProperty("jdbc.driverClassName");
            PGDB.connectionURL = prop.getProperty("jdbc.url");
            PGDB.username = prop.getProperty("jdbc.username");
            PGDB.password = prop.getProperty("jdbc.password");

//            System.out.println("************\n"+drivers);
//            System.out.println(connectionURL);
//            System.out.println(username);
//            System.out.println(password);
//            System.out.println(PGDATA);

        }catch (Exception e)
        {
            System.out.println("cannot load database properties file");
            e.printStackTrace();
        }

    }

    public static PGDB getInstance() {
        if (instance == null) {
            instance = new PGDB();
            instance.startConnection();
        }
        return instance;
    }
//    public boolean startDBServer()
//    {
//        System.out.println("Starting DB SERVER server...");
//        String cmd = "cd "+PGDB.PGDATA+"\\bin && ";
//        cmd += "pg_ctl -D "+PGDB.PGDATA+ "\\data -l logfile start\n";
//        try {
//            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \""+PGDB.PGDATA.substring(0,2)+" && "+cmd+"\"");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(cmd);
//        startConnection();
//        return true;
//    }
    @Override
    public boolean startConnection() {
        System.out.println("Starting connection : "+drivers+connectionURL+username+password);
        try {
            Class.forName(drivers);
            this.c = DriverManager
                    .getConnection(connectionURL,
                            username, password);
            c.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        System.out.println("Opened database successfully");
        return true;
    }

    @Override
    public boolean execute(String sql,boolean commit) {
        System.out.println(sql);
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            if(commit)
                commit();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Records created successfully");
        return true;
    }
    public boolean commit()
    {
        try {
            c.commit();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean rollback() {
        try {
            c.rollback();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public ResultSet getData(String sql) {
        System.out.println(sql);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
            c.commit();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            e.getStackTrace();

        }
        return rs;
    }

    @Override
    public boolean close() {
        return false;
    }
}



