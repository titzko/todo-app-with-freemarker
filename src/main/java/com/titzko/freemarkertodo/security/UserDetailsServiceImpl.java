package com.titzko.freemarkertodo.security;

import com.titzko.freemarkertodo.model.MyUser;
import com.titzko.freemarkertodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = userRepository.getUserByUsername(username);

        if (myUser == null) {
            throw new UsernameNotFoundException("Couldnt find the user");
        }

        return new MyUserDetails(myUser);
    }
}
