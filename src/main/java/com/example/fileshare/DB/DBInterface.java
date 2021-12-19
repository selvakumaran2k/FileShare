package com.example.fileshare.DB;

import java.sql.ResultSet;

public interface DBInterface {
    boolean startConnection();

    boolean execute(String sql,boolean commit);

    ResultSet getData(String sql);

    boolean close();

    boolean commit();

    boolean rollback();
}
