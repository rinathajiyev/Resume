package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDao implements SkillDaoInter {

    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            stmt.execute("select * from skill");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                Skill s = new Skill(id, name);
                result.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean removeSkill(int id) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("delete from skill where id = ?");
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSkill(Skill skill) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("update skill set name = ? where id = ?");
            stmt.setString(1, skill.getName());
            stmt.setInt(2, skill.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertSkill(Skill skill) {
        boolean result = true;
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("insert into skill(name) values(?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, skill.getName());
            result = stmt.execute();

            ResultSet generatedKeys = stmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                skill.setId(generatedKeys.getInt(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }

        return result;

    }

}
