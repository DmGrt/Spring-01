package com.springhw.dao;

import com.springhw.models.User;
import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();
}
