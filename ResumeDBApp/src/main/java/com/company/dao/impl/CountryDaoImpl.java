package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDao implements CountryDaoInter {
   
    @Override
    public List<Country> getAll() {
        List<Country> result = new ArrayList<>();
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            stmt.execute("select country.name from country");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                String name = rs.getString("name");
                Country ctr = new Country(null, name, null);
                result.add(ctr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
