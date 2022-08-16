package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import java.sql.Connection;
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
            stmt.execute("select skill.name from skill");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                String name = rs.getString("name");
                Skill s = new Skill(null, name);
                result.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
