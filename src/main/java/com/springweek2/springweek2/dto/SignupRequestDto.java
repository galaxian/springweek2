package com.springweek2.springweek2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SignupRequestDto {
    private String username;
    private String password;
    private String checkPassword;
}
