package com.koda.demo.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koda.demo.GoogleUser;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User getOrSave(GoogleUser google){
        User savedUser = userRepository.findByEmail(google.getEmail());

        if(savedUser == null){
            User newUser = google.toEntity();
            newUser.addRole(new UserRole("ROLE_USER"));
            savedUser = userRepository.save(newUser);
        }

        return savedUser;
    }
}
