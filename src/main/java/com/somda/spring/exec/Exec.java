package com.somda.spring.exec;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.somda.spring.configuration.AppConfig;
import com.somda.spring.entity.User;
import com.somda.spring.services.UserService;

public class Exec {

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = context.getBean(UserService.class);
		User user = new User();
		user.setName("Matheus");
		user.setPassword("teste");
		user.setUserName("login");

		userService.saveUser(user);
		context.close();

	}

}
