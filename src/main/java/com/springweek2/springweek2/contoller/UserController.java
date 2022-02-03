package com.springweek2.springweek2.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springweek2.springweek2.dto.SignupRequestDto;
import com.springweek2.springweek2.security.UserDetailsImpl;
import com.springweek2.springweek2.service.KakaoUserService;
import com.springweek2.springweek2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    @PostMapping("/user/signup")
    public String resisterUser(SignupRequestDto requestDto) {
        userService.resisterUser(requestDto);
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.login(userDetails);
    }

    @GetMapping("/user/signup")
    public String signup(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.signup(userDetails);
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}
