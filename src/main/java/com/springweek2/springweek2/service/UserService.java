package com.springweek2.springweek2.service;

import com.springweek2.springweek2.dto.SignupRequestDto;
import com.springweek2.springweek2.model.User;
import com.springweek2.springweek2.model.UserRoleEnum;
import com.springweek2.springweek2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String resisterUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();

        if (!username.matches("^[A-Za-z\\d]{3,}$")) {
            throw new NullPointerException("아이디는 3자 이상 대소문자와 숫자만 사용가능합니다");
        }
        if (requestDto.getPassword().contains(username)){
            throw new NullPointerException("비밀번호에 아이디는 포함할 수 없습니다.");
        }

        if (requestDto.getPassword().length() < 4) {
            throw new NullPointerException("비밀번호는 4자 이상 가능합니다.");
        }

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        String password = passwordEncoder.encode(requestDto.getPassword());
        String checkPassword = requestDto.getCheckPassword();
        if (!requestDto.getPassword().equals(checkPassword)) {
            throw new NullPointerException("비밀번호와 비밀번호 확인의 값이 일치하지 않습니다.");
        }
        UserRoleEnum role = UserRoleEnum.USER;

        User user = new User(username, password, role);
        userRepository.save(user);
        return "success";
    }
}
