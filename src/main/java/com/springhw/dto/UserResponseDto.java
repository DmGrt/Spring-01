package com.springhw.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String password;
}
