package com.springhw.service;

import com.springhw.dao.UserDao;
import com.springhw.models.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public User get(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }
}
