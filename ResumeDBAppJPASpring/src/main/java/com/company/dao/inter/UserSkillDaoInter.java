package com.company.dao.inter;

import com.company.entity.*;

import java.util.*;

public interface UserSkillDaoInter {
    
    public List<UserSkill> getAllSkillByUserId(int userId);
    
    public boolean updateUserSkill(UserSkill user);
    
    public boolean removeUserSkill(int id);
    
    public boolean insertUserSkill(UserSkill user);
       
}
