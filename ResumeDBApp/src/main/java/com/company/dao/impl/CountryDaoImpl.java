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
    public List<Country> getAllCountries() {
        List<Country> result = new ArrayList<>();
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            stmt.execute("select country.id, country.name from country");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Country ctr = new Country(id, name, null);
                result.add(ctr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Country> getAllNationalities() {
        List<Country> result = new ArrayList<>();
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            stmt.execute("select country.id, country.nationality from country");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nationalityName = rs.getString("nationality");
                Country ctr = new Country(id, null, nationalityName);
                result.add(ctr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
