package com.titzko.freemarkertodo.service;

import com.titzko.freemarkertodo.model.MyUser;
import com.titzko.freemarkertodo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class RegisterServiceTest {


    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    RegisterService registerService;

    @BeforeEach
    void setUp() {
        this.userRepository = mock(UserRepository.class);
        this.bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        registerService = new RegisterService(userRepository, bCryptPasswordEncoder);
    }


    @Test
    void registerUser_newUser() {
        MyUser user = new MyUser("mati", "abc", "user", true);

        expect(this.userRepository.getUserByUsername(user.getUsername())).andReturn(null);
        expect(bCryptPasswordEncoder.encode(user.getPassword())).andReturn("abc");
        expect(userRepository.save(user)).andReturn(user);

        replay( bCryptPasswordEncoder, userRepository);
        boolean result = this.registerService.registerUser(user);
        verify( bCryptPasswordEncoder, userRepository);

        assertTrue(result);
    }


    @Test
    void registerUser_userAlreadyExist() {
        MyUser user = new MyUser("mati", "abc", "user", true);

        expect(this.userRepository.getUserByUsername(user.getUsername())).andReturn(user);

        replay(userRepository);
        boolean result = this.registerService.registerUser(user);
        verify(userRepository);

        assertFalse(result);
    }
}