package com.springweek2.springweek2.service;

import com.springweek2.springweek2.dto.SignupRequestDto;
import com.springweek2.springweek2.model.User;
import com.springweek2.springweek2.model.UserRoleEnum;
import com.springweek2.springweek2.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("아이디 2글자 이하")
    void userResisterFailId1() {
        SignupRequestDto requestDto = new SignupRequestDto(
                "ab",
                "abcd1234",
                "abcd1234"
        );

        UserService userService = new UserService(userRepository, passwordEncoder);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.resisterUser(requestDto);
        });

        assertEquals("아이디는 3자 이상 대소문자와 숫자만 사용가능합니다",
                exception.getMessage()

        );
    }

    @Test
    @DisplayName("특수문자 사용")
    void userResisterFailId2() {
        SignupRequestDto requestDto = new SignupRequestDto(
                "안녕+_+!!!!",
                "abcd1234",
                "abcd1234"
        );

        UserService userService = new UserService(userRepository, passwordEncoder);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.resisterUser(requestDto);
        });

        assertEquals("아이디는 3자 이상 대소문자와 숫자만 사용가능합니다",
                exception.getMessage()

        );
    }

    @Test
    @DisplayName("비밀번호에 아이디 포함")
    void userResisterFailPw1() {
        SignupRequestDto requestDto = new SignupRequestDto(
                "abcd",
                "abcd1234",
                "abcd1234"
        );

        UserService userService = new UserService(userRepository, passwordEncoder);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.resisterUser(requestDto);
        });

        assertEquals("비밀번호에 아이디는 포함할 수 없습니다.",
                exception.getMessage()

        );
    }

    @Test
    @DisplayName("비밀번호에 아이디 포함")
    void userResisterFailPw2() {
        SignupRequestDto requestDto = new SignupRequestDto(
                "abcd1234",
                "abcd1234",
                "abcd1234"
        );

        UserService userService = new UserService(userRepository, passwordEncoder);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.resisterUser(requestDto);
        });

        assertEquals("비밀번호에 아이디는 포함할 수 없습니다.",
                exception.getMessage()

        );
    }

    @Test
    @DisplayName("비밀번호 확인과 비밀번호 불일치")
    void userResisterFailPw3() {
        SignupRequestDto requestDto = new SignupRequestDto(
                "mario",
                "abcd1234",
                "qwer9876"
        );

        UserService userService = new UserService(userRepository, passwordEncoder);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.resisterUser(requestDto);
        });

        assertEquals("비밀번호와 비밀번호 확인의 값이 일치하지 않습니다.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("비밀번호 확인과 비밀번호 불일치")
    void userResisterFailPw4() {
        SignupRequestDto requestDto = new SignupRequestDto(
                "mario",
                "abcd1234o",
                "abcd12340"
        );

        UserService userService = new UserService(userRepository, passwordEncoder);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.resisterUser(requestDto);
        });

        assertEquals("비밀번호와 비밀번호 확인의 값이 일치하지 않습니다.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("아이디 중복")
    void userResisterFailId4() {
        SignupRequestDto requestDto = new SignupRequestDto(
                "mario",
                "abcd1234",
                "abcd1234"
        );

        User user = new User(requestDto.getUsername(), "kdas214", UserRoleEnum.USER, null);

        UserService userService = new UserService(userRepository, passwordEncoder);
        when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.resisterUser(requestDto);
        });

        assertEquals("이미 존재하는 아이디입니다.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("아이디 중복")
    void userResisterFailId5() {
        SignupRequestDto requestDto = new SignupRequestDto(
                "sparta0110",
                "abcd1234",
                "abcd1234"
        );

        User user = new User(requestDto.getUsername(), "kdas214", UserRoleEnum.USER, null);

        UserService userService = new UserService(userRepository, passwordEncoder);
        when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.resisterUser(requestDto);
        });

        assertEquals("이미 존재하는 아이디입니다.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("회원가입 성공")
    void userResisterNormal() {
        SignupRequestDto requestDto = new SignupRequestDto(
                "mario",
                "abcd1234",
                "abcd1234"
        );

        UserService userService = new UserService(userRepository, passwordEncoder);

        String result = userService.resisterUser(requestDto);

        assertEquals("success", result);
    }

    @Test
    @DisplayName("회원가입 성공2")
    void userResisterNormal2() {
        SignupRequestDto requestDto = new SignupRequestDto(
                "SOnecu932742",
                "vKe3242aNe13ds23OOo0",
                "vKe3242aNe13ds23OOo0"
        );

        UserService userService = new UserService(userRepository, passwordEncoder);

        String result = userService.resisterUser(requestDto);

        assertEquals("success", result);
    }
}