package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.*;
import com.company.entity.Country;
import com.company.entity.User;
import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String password = rs.getString("password");
        String address = rs.getString("address");
        String phone = rs.getString("phone");
        Date date = rs.getDate("birthdate");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);

        return new User(id, name, surname, email, profileDesc, password, address, phone, date, nationality, birthplace);
    }

    private User getUserSimple(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String password = rs.getString("password");
        String address = rs.getString("address");
        String phone = rs.getString("phone");
        Date date = rs.getDate("birthdate");

        return new User(id, name, surname, email, profileDesc, password, address, phone, date, null, null);
    }

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try {
            Connection c = connect();

            String sql = "select\n"
                    + "	u.*,\n"
                    + "	n.nationality,\n"
                    + "	c.name as birthplace\n"
                    + "from user u \n"
                    + "	left join country n on u.nationality_id = n.id\n"
                    + "	left join country c on u.birthplace_id = c.id where 1=1";

            if(name != null && !name.trim().isEmpty()){
                sql += " and u.name = ?";
            }

            if(surname != null && !surname.trim().isEmpty()){
                sql += " and u.surname = ?";
            }

            if(nationalityId != null){
                sql += " and u.nationality_id = ?";
            }

            PreparedStatement stmt = c.prepareStatement(sql);

            int i = 1;
            if(name != null && !name.trim().isEmpty()){
                stmt.setString(i, name);
                i++;
            }

            if(surname != null && !surname.trim().isEmpty()){
                stmt.setString(i, surname);
                i++;
            }

            if(nationalityId != null){
                stmt.setInt(i, nationalityId);
            }

            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = null;
        try (Connection c = connect()){
            PreparedStatement statement = c.prepareStatement("select * from user where email=? and password=?");
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result = getUserSimple(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = null;
        try (Connection c = connect()){
            PreparedStatement statement = c.prepareStatement("select * from user where email=?");
            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result = getUserSimple(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("update user set name = ?, surname = ?,email = ?, phone = ?, profile_description = ?, address = ?, birthdate = ?, birthplace_id = ?, nationality_id = ? where id = ?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getProfileDesc());
            stmt.setString(6, u.getAddress());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setInt(9, u.getNationality().getId());
            stmt.setInt(10, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user where id = " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            stmt.execute("select\n"
                    + "	u.*,\n"
                    + "	n.nationality,\n"
                    + "	c.name as birthplace\n"
                    + "from user u \n"
                    + "	left join country n on u.nationality_id = n.id\n"
                    + "	left join country c on u.birthplace_id = c.id where u.id = " + userId);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("insert into user(name, surname, email, phone, profile_description, password, address, birthdate, birthplace_id, nationality_id) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getProfileDesc());
            stmt.setString(6, crypt.hashToString(4, u.getPassword().toCharArray()));
            stmt.setString(7, u.getAddress());
            stmt.setDate(8, u.getBirthDate());
            stmt.setInt(9, u.getBirthPlace().getId());
            stmt.setInt(10, u.getNationality().getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
