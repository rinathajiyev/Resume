package com.company.main;

import com.company.dao.inter.UserDaoInter;
import java.util.List;
import com.company.entity.User;

public class Main {

    public static void main(String[] args) {
        UserDaoInter dao = Context.instanceUserDao();
        
        User u = dao.findByEmailAndPassword("rinathajiyev@gmail.com", "12345r");
        System.out.println(u);
    }
}
