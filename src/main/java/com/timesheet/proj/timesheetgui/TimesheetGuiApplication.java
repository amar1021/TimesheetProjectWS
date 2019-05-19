package com.timesheet.proj.timesheetgui;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.timesheet.proj.timesheetgui.entity.User;
import com.timesheet.proj.timesheetgui.respository.UserRepository;

@SpringBootApplication
public class TimesheetGuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetGuiApplication.class, args);
	}
	/*@Bean
    protected CommandLineRunner init(final UserRepository userRepository) {

        return args -> {
            User user = new User();
            user.setRole("admin");
            user.setUsername("admin");
            user.setPassword("admin");
            user.setName("Administrator");
            user.setEmail("admin@javahelps.com");
            userRepository.save(user);

        };
    }*/
}
