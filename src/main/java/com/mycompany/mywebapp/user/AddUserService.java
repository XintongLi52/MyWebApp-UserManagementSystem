package com.mycompany.mywebapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddUserService {
    @Autowired UserRepository userRepository;
    public void addUser(User user) {
        userRepository.save(user);
    }
}
