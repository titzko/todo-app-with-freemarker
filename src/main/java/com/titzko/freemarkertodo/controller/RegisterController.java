package com.titzko.freemarkertodo.controller;

import com.titzko.freemarkertodo.model.MyUser;
import com.titzko.freemarkertodo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("")
    ResponseEntity<String> login(@RequestBody @Valid MyUser myUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Username or password invalid", HttpStatus.BAD_REQUEST);
        }

        boolean validUser = this.registerService.registerUser(myUser);
        if (!validUser) {
            return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("created", HttpStatus.OK);

    }
}
