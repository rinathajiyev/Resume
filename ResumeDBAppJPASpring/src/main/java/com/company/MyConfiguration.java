package com.company;

import org.springframework.context.annotation.*;

@Configuration
@Scope("prototype")
public class MyConfiguration {

    public MyConfiguration(){
        System.out.println("MyConfiguration");
    }

    @Bean("obj")
    public Object getObject(){
        System.out.println("object created");
        return new Object();
    }
}
