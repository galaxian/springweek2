package com.springweek2.springweek2.contoller;

import com.springweek2.springweek2.dto.SignupRequestDto;
import com.springweek2.springweek2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public String resisterUser(SignupRequestDto requestDto) {
        userService.resisterUser(requestDto);
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }
}
