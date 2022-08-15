package com.company.main;

import com.company.bean.User;
import com.company.dao.impl.UserDaoImpl;
import com.company.dao.inter.UserDaoInter;
import java.util.List;

public class Main {

    public static void main(String[] args) {        
        UserDaoInter userDao = new UserDaoImpl();
        List<User> list = userDao.getAll();
        
        for(User u: list){
            System.out.println(u);
        }        
        
    }
}
