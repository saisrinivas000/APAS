package com.APAS.demo.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.APAS.demo.model.User;
import com.APAS.demo.security.JwtUtil;
import com.APAS.demo.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){
		User savedUser = userService.registerUser(user);
		return ResponseEntity.ok(savedUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> credentials){
	    String email = credentials.get("email");
	    String password = credentials.get("password");

	    Optional<User> userOpt = userService.findByEmail(email);

	    if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)){
	        User user = userOpt.get();
	        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
	        return ResponseEntity.ok(Map.of("token", token));
	    } else { 
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
	}

}
