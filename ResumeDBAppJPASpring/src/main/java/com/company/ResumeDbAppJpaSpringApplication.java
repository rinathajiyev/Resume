package com.company;

import com.company.dao.impl.*;
import com.company.dao.inter.*;
import com.company.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import java.util.*;

@SpringBootApplication
public class ResumeDbAppJpaSpringApplication {

	@Autowired
	@Qualifier("userDao1")
	@Lazy
	private UserDaoInter userDao;

	public static void main(String[] args) {
		SpringApplication.run(ResumeDbAppJpaSpringApplication.class, args);

	}

	@Bean
	public CommandLineRunner run (){
		CommandLineRunner clr = new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				 User user = userDao.findByEmail("rinathajiyev@gmail.com");
				 System.out.println("user = " + user);
			}
		};

		return clr;
	}

}
