package com.springweek2.springweek2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostRequestDto {
    private String title;
    private String username;
    private String contents;
}
