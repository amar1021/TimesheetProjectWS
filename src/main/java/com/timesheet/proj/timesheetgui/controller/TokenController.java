package com.timesheet.proj.timesheetgui.controller;

import java.util.HashMap;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timesheet.proj.timesheetgui.entity.User;
import com.timesheet.proj.timesheetgui.entity.UserLogin;
import com.timesheet.proj.timesheetgui.respository.UserRepository;
import com.timesheet.proj.timesheetgui.security.JwtGenerator;

import io.jsonwebtoken.lang.Objects;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/login")
public class TokenController {

	@Autowired
	private UserRepository repository;

	@GetMapping
	public Iterable<User> findAll() {
		return repository.findAll();
	}

    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @GetMapping(path = "/{username}")
    public Optional<User> find(@PathVariable("username") String username) {
        return repository.findById(username);
    }
    
    @PostMapping(path = "/getToken")
    public ResponseEntity<?> generate(@RequestBody final User user) {
    	
    	HashMap<String, String> str = new HashMap<>();
    	Optional<User> userData = repository.findById(user.getUsername());
    	userData.ifPresent(value -> {
    		str.put("username", value.getUsername());
    		str.put("email", value.getEmail());
    		str.put("name", value.getName());
    		str.put("role", value.getRole());
    		str.put("id", value.getId().toString());
    		str.put("token", jwtGenerator.generate(user));
    	    System.out.println("Value found - " + value);
    	});
    	
    	if(userData.isPresent()) {
    		if(userData.get().getPassword().equals(user.getPassword())) {
    			return new ResponseEntity<>(str, HttpStatus.OK);
    		}
    		else {
    			return new ResponseEntity<>("Wrong password", HttpStatus.NOT_ACCEPTABLE);
    		}
    		
    	}else
    	    return new ResponseEntity<>("User not found", HttpStatus.NOT_ACCEPTABLE);
    }
}
