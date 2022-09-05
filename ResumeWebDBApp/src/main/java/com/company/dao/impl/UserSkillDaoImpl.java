package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userSkillId = rs.getInt("userSkillId");
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        int power = rs.getInt("power");
        String skillName = rs.getString("skill_name");

        return new UserSkill(userSkillId, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("SELECT\n"
                    + "	u.*,\n"
                    + " us.id as userSkillId,\n"
                    + "	us.skill_id,\n"
                    + "	us.power,\n"
                    + "	s.NAME AS skill_name \n"
                    + "FROM\n"
                    + "	user_skill us\n"
                    + "	LEFT JOIN USER u ON us.user_id = u.id\n"
                    + "	LEFT JOIN skill s ON s.id = us.skill_id \n"
                    + "WHERE\n"
                    + "	us.user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean updateUserSkill(UserSkill u) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("update user_skill set user_id = ?, skill_id = ?, power = ? where id = ?");
            stmt.setInt(1, u.getUserId().getId());
            stmt.setInt(2, u.getSkillId().getId());
            stmt.setInt(3, u.getPower());
            stmt.setInt(4, u.getId());
            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUserSkill(int id) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("delete from user_skill where id = ?");
            stmt.setInt(1, id);
            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertUserSkill(UserSkill userSkill) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("insert into user_skill(skill_id, user_id, power) values(?, ?, ?)");
            stmt.setInt(1, userSkill.getSkillId().getId());
            stmt.setInt(2, userSkill.getUserId().getId());
            stmt.setInt(3, userSkill.getPower());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
