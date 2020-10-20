package com.springhw.dto;

import com.springhw.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {
    private User user;
}
