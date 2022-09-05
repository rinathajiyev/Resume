package com.company.dao.inter;

import com.company.entity.EmployementHistory;
import java.util.List;

public interface EmployementHistoryDaoInter {
    
    public List<EmployementHistory> getAllEmploymentHistoryByUserId(int userId);
    
    public boolean updateHistory(EmployementHistory history);
    
    public boolean insertHistory(EmployementHistory history);
    
    public boolean removeHistory(int id);
       
}
