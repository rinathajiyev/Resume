package com.company.dao.inter;

import javax.persistence.*;
import java.sql.*;

public class AbstractDao {

    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "rinathajiyev2002";

        Connection c = DriverManager.getConnection(url, username, password);

        return c;
    }

    private static EntityManagerFactory emf = null;

    public EntityManager em() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("resumeappPU");
        }
        EntityManager entityManager = emf.createEntityManager();

        return entityManager;
    }
    
    public void close(){
        emf.close();
    }
}
