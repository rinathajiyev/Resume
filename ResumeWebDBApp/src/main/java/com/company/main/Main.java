package com.company.main;

import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.Country;
import com.company.entity.Skill;
import java.util.List;
import com.company.dao.inter.EmployementHistoryDaoInter;
import com.company.entity.User;

public class Main {

    public static void main(String[] args) {
        UserDaoInter userDao = Context.instanceUserDao();
        List<User> list = userDao.getAll("Rinat", "Hajiyev", 2);

        for(User u: list){
            System.out.println(u.getNationality().getNationality());
        }
    }
}
