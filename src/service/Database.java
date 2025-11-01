package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static Database instance;

    private Database() {}

    public static Database getInstance(){
        if(instance == null) {
            instance = new Database();
        }

        return instance;
    }

}
