package com.company.dao.inter;

import com.company.entity.*;

import java.util.*;

public interface EmployementHistoryDaoInter {
    
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);
    
    public boolean updateHistory(EmploymentHistory history);
    
    public boolean insertHistory(EmploymentHistory history);
    
    public boolean removeHistory(int id);
       
}
