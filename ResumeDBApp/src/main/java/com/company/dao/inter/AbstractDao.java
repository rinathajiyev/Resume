package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDao {

    public Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "12345";

        Connection c = DriverManager.getConnection(url, username, password);
        
        return c;
    }
}
