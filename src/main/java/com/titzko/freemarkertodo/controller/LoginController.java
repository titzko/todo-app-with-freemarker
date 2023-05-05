package com.titzko.freemarkertodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    String login() {
        return "login/login";
    }

    @GetMapping("/fail")
    String login(Model model) {
        model.addAttribute("failedLogin", true);
        return "login/login";
    }


}
