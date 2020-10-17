package com.springhw.service;

import com.springhw.models.User;
import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();
}
