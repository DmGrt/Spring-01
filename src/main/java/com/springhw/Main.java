package com.springhw;

import com.springhw.config.AppConfig;
import com.springhw.models.User;
import com.springhw.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        User tom = new User();
        tom.setEmail("tom@tom.com");
        tom.setPassword("tom");

        userService.add(tom);

        User jerry = new User();
        jerry.setEmail("jerry@jerry.com");
        jerry.setPassword("jerry");

        userService.add(jerry);

        User butch = new User();
        butch.setEmail("butch@butch.com");
        butch.setPassword("butch");

        userService.add(butch);

        userService.listUsers().forEach(System.out::println);
    }
}
