package com.titzko.freemarkertodo.service;

import com.titzko.freemarkertodo.model.MyUser;
import com.titzko.freemarkertodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegisterService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean registerUser(MyUser myUser) {

        MyUser userByUsername = this.userRepository.getUserByUsername(myUser.getUsername());

        if (userByUsername != null) {
            return false;
        }

        myUser.setPassword(bCryptPasswordEncoder.encode(myUser.getPassword()));
        myUser.setRole("User");
        myUser.setEnabled(true);
        userRepository.save(myUser);
        return true;
    }
}
