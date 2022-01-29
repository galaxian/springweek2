package com.springweek2.springweek2.service;

import com.springweek2.springweek2.dto.SignupRequestDto;
import com.springweek2.springweek2.model.User;
import com.springweek2.springweek2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void resisterUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();

        if (!username.matches("^[A-Za-z\\d]{3,}$")) {
            throw new IllegalArgumentException("아이디는 3자 이상 대소문자와 숫자만 사용가능합니다");
        }

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        String password = passwordEncoder.encode(requestDto.getPassword());
        String checkPassword = requestDto.getCheckPassword();
        if (!requestDto.getPassword().equals(checkPassword)) {
            throw new IllegalArgumentException("비밀번호를 다시 확인해 주세요");
        }

        User user = new User(username, password);
        userRepository.save(user);
    }
}
