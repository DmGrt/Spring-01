package com.springhw.controllers;

import com.springhw.dto.UserResponseDto;
import com.springhw.models.User;
import com.springhw.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String injectUsers() {
        User jakob = new User();
        jakob.setPassword("jane");
        jakob.setEmail("bokaj@jakob.ja");
        userService.add(jakob);

        User price = new User();
        price.setPassword("price");
        price.setEmail("MW141@activision.com");
        userService.add(price);

        User soap = new User();
        soap.setPassword("soap");
        soap.setEmail("KIAman@activision.com");
        userService.add(soap);

        User gaz = new User();
        gaz.setPassword("fruitMonster");
        gaz.setEmail("justGazz@activision.com");
        userService.add(gaz);

        return "Users injected";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return mapUserToDto(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(this::mapUserToDto)
                .collect(Collectors.toList());
    }

    private UserResponseDto mapUserToDto(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
