package com.APAS.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.APAS.demo.model.User;
import com.APAS.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userrepo;

    public User registerUser(User user) {
        if (userrepo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email is already Registered..!");
        }
        return userrepo.save(user);
    }

    public Optional<User> findById(Long id) {
        return userrepo.findById(id);
    }

    public List<User> getAllAthletes() {
        return userrepo.findAllByRole(User.Role.ATHLETE);
    }

    public Optional<User> findByEmail(String email) {
        return userrepo.findByEmail(email);
    }
}
