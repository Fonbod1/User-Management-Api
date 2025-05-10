package com.KF48.User.Management;

import com.KF48.User.Management.UserModel.Role;
import com.KF48.User.Management.UserModel.User;
import com.KF48.User.Management.UserRepository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class UserManagementApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(UserManagementApplication.class);

	@Autowired
	private UserRepository userRepository;

	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder; // Use the bean defined in SecurityConfiguration

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}


	@Override
	public void run(String... args) {
		logger.info("Checking for existing admin account...");
		List<User> adminAccounts = userRepository.findByRole(Role.ADMIN);
		if (adminAccounts.isEmpty()) {
			logger.info("No admin account found. Creating default admin account...");
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setFirstname("Admin");
			user.setSecondname("Admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
			logger.info("Default admin account created successfully.");
		} else {
			logger.info("Admin account already exists.");
		}
	}
}