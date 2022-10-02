package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.company.dao.inter.EmployementHistoryDaoInter;
import com.company.entity.EmploymentHistory;

public class EmployementHistoryDaoImpl extends AbstractDao implements EmployementHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
        String header = rs.getString("header");
        String jobDescription = rs.getString("job_description");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        int userId = rs.getInt("user_id");
        int historyId = rs.getInt("historyId");
//        EmploymentHistory emp = new EmploymentHistory(historyId, header, beginDate, endDate, jobDescription, new User(userId));
//        return emp;
return null;
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("SELECT\n"
                    + "	u.*,\n"
                    + " eh.id as historyId, \n"
                    + "	eh.begin_date,\n"
                    + "	eh.end_date,\n"
                    + "	eh.header,\n"
                    + "	eh.job_description, \n"
                    + " eh.user_id \n"
                    + "FROM\n"
                    + "	employment_history eh\n"
                    + "	LEFT JOIN USER u ON eh.user_id = u.id\n"
                    + "WHERE\n"
                    + "	eh.user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
//                EmployementHistory emp = getEmploymentHistory(rs);
//                result.add(emp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean updateHistory(EmploymentHistory history) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("update employment_history set begin_date = ?, end_date = ?, header = ?, job_description = ? where id = ?");
//            stmt.setDate(1, history.getBeginDate());
//            stmt.setDate(2, history.getEndDate());
            stmt.setString(3, history.getHeader());
            stmt.setString(4, history.getJobDescription());
            stmt.setInt(5, history.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertHistory(EmploymentHistory history) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("insert into employment_history(header, begin_date, end_date, job_description, user_id) values(?,?,?,?,?)");
            stmt.setString(1, history.getHeader());
//            stmt.setDate(2, history.getBeginDate());
//            stmt.setDate(3, history.getEndDate());
            stmt.setString(4, history.getJobDescription());
            stmt.setInt(5, history.getUser().getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeHistory(int id) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareStatement("delete from employment_history where id = ?");
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
