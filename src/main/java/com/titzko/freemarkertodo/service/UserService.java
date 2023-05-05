package com.titzko.freemarkertodo.service;

import com.titzko.freemarkertodo.model.MyUser;
import com.titzko.freemarkertodo.security.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public MyUser getUser() {
        return ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMyUser();
    }
}
