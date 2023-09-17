package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

//	CommandLineRunner run(UserService userService) {
//		return args -> {
//			userService.saveRoleForUser(new Role("ROLE_ADMIN"));
//			userService.saveUser(new User("admin", "admin", 30, "123"));
//
//		};
//	}

}
