package com.springhw.service;

import com.springhw.models.User;
import java.util.List;

public interface UserService {
    void add(User user);

    User get(Long id);

    List<User> listUsers();
}
