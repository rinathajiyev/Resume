package com.company.main;

import com.company.dao.impl.CountryDaoImpl;
import com.company.dao.impl.EmployementHistoryDaoImpl;
import com.company.dao.impl.SkillDaoImpl;
import com.company.dao.impl.UserDaoImpl;
import com.company.dao.impl.UserSkillDaoImpl;
import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.dao.inter.EmployementHistoryDaoInter;

public class Context {
    
    public static UserDaoInter instanceUserDao(){
        return new UserDaoImpl();
    }
    
    public static UserSkillDaoInter instanceUserSkillDao(){
        return new UserSkillDaoImpl();
    }
    
    public static EmployementHistoryDaoInter instanceEmployementHistoryDao(){
        return new EmployementHistoryDaoImpl();
    }
    
    public static CountryDaoInter instanceCountryDao(){
        return new CountryDaoImpl();
    }
    
    public static SkillDaoInter instanceSkillDao(){
        return new SkillDaoImpl();
    }

}
