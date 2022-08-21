package com.company.main;

import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Country;
import com.company.entity.Skill;
import java.util.List;

public class Main {

    public static void main(String[] args) {        
        EmploymentHistoryDaoInter list = Context.instanceEmploymentHistoryDao();

        System.out.println(list.getAllEmploymentHistoryByUserId(1));

        CountryDaoInter countryDao = Context.instanceCountryDao();
        List<Country> l1 = countryDao.getAllCountries();
        
        for(Country c: l1){
            System.out.println(c);
        }
        
        SkillDaoInter skillDao = Context.instanceSkillDao();
        List<Skill> l2 = skillDao.getAll();
        
        for(Skill s: l2){
            System.out.println(s);
        }   
    }
}
